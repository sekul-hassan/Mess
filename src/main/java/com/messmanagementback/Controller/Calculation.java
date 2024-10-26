package com.messmanagementback.Controller;

import com.messmanagementback.Model.Cost;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Model.Summary;
import com.messmanagementback.Repository.CostRepository;
import com.messmanagementback.Repository.MessInfoRepository;
import com.messmanagementback.Service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Component
@CrossOrigin("http://localhost:3000")
public class Calculation {

    @Autowired
    private CalculationService calculationService;
    @Autowired
    private CostRepository costRepository;
    @Autowired
    private MessInfoRepository messInfoRepository;

    @GetMapping("calculation/{messId}")
    public Summary calculate(@PathVariable String messId){
//        return calculationService.calculate(messId);
        return null;
    }

//    @GetMapping("test/{messId}")
//    public List<Cost> getCost(@PathVariable String messId){
//        MessInfo messInfo = messInfoRepository.findById(messId).orElse(null);
//        return costRepository.findAllByMessInfoAndYearAndMonth(messInfo,2023,6);
//    }

}
