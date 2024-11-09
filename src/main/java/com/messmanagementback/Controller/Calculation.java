package com.messmanagementback.Controller;

import com.messmanagementback.Model.Cost;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Model.Summary;
import com.messmanagementback.Repository.CostRepository;
import com.messmanagementback.Repository.MessInfoRepository;
import com.messmanagementback.Service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Component
@CrossOrigin("http://localhost:3000")
public class Calculation {

    @Autowired
    private CalculationService calculationService;

    @GetMapping("calculation/{messId}/{month}/{year}")
    public ResponseEntity<Map<String , Object>> calculate(
            @PathVariable String messId,
            @PathVariable int month,
            @PathVariable int year
    ){
        return calculationService.calculate(messId,month,year);
    }


}
