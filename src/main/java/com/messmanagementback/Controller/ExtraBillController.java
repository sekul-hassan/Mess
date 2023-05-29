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
        return extraBillService.saveBill(extraBill);
    }

}
