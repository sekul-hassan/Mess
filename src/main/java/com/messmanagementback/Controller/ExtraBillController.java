package com.messmanagementback.Controller;

import com.messmanagementback.Model.ExtraBill;
import com.messmanagementback.Model.Member;
import com.messmanagementback.Service.ExtraBillService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Component
@RestController
@CrossOrigin("http://localhost:3000")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraBillController {

    @Autowired
    private ExtraBillService extraBillService;

    @GetMapping("/extraBillIs/{messId}/{year}/{month}")
    public ResponseEntity<Map<String,Object>> allExtraBillOfAMess(
            @PathVariable String messId,
            @PathVariable int year,
            @PathVariable int month

    ){
        return extraBillService.findExtraBill(messId,year,month);
    }

    @PostMapping("/saveBill/{messId}")
    public ResponseEntity<Map<String ,Object>> saveBill(@RequestBody ExtraBill extraBill, @PathVariable String messId){
        return extraBillService.saveBill(extraBill,messId);
    }

    @PutMapping("/updateBill/{id}")
    public ResponseEntity<Map<String ,Object>> updateMess(@RequestBody ExtraBill extraBill,@PathVariable Long id){
       return extraBillService.updateBill(extraBill,id);
    }

    @DeleteMapping("/deleteExtraBill/{id}")
    public ResponseEntity<Map<String, Object>> deleteExtraBill(@PathVariable Long id) {
        return extraBillService.deleteExtraBill(id);
    }


}
