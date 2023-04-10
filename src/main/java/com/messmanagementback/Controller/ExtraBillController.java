package com.messmanagementback.Controller;

import com.messmanagementback.Model.ExtraBill;
import com.messmanagementback.Service.ExtraBillService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Component
@RestController
@CrossOrigin("http://localhost:3001")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraBillController {

    @Autowired
    private ExtraBillService extraBillService;

    @PostMapping("/saveBill")
    public ExtraBill saveBill(@RequestBody ExtraBill extraBill){
        System.out.println(extraBill);
        return extraBillService.saveBill(extraBill);
    }
    @GetMapping("/getMess/{id}")
    public Optional<ExtraBill> getOneMess(@PathVariable String id){
        return extraBillService.getMess(id);
    }

    @PutMapping("/getMess/{id}")
    public ExtraBill updateMess(@RequestBody ExtraBill extraBill, @PathVariable String id){
        Optional<ExtraBill> existingOptional = extraBillService.getMess(id);
        ExtraBill existing = existingOptional.orElse(null);
        if(extraBill.getWifiBill()!=0){
            existing.setWifiBill(extraBill.getWifiBill());
        }
        if(extraBill.getFixedMeal()!=0){
            existing.setFixedMeal(extraBill.getFixedMeal());
        }
        if(extraBill.getKhalaBill()!=0){
            existing.setKhalaBill(extraBill.getKhalaBill());
        }

        return extraBillService.saveBill(existing);
    }

    @DeleteMapping("/getMess/{id}")
    public String deleteMess(@PathVariable String id){
        return extraBillService.deleteMess(id);
    }

}
