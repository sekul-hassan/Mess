package com.messmanagementback.Controller;
import com.messmanagementback.Model.Cost;
import com.messmanagementback.Service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class CostController {

    @Autowired
    private CostService costService;

    @PutMapping("/saveCost/{id}")
    public Cost saveCost(@RequestBody Cost cost,@PathVariable Long id){
        return costService.saveCost(cost,id);
    }

    @PostMapping("/saveCost/{messId}")
    public Cost addCost(@RequestBody Cost cost,@PathVariable String messId){
        return costService.addCost(cost,messId);
    }


    @GetMapping("getAllCost/{messId}")
    public List<Cost> getAllCost(@PathVariable String messId){
        return costService.getAllCost(messId);
    }

    @DeleteMapping("costDelete/{id}")
    public Cost deleteCost(@PathVariable Long id){
        return costService.deleteCost(id);
    }

}
