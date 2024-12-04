package com.messmanagementback.Controller;
import com.messmanagementback.Model.Cost;
import com.messmanagementback.Service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class CostController {

    @Autowired
    private CostService costService;

    @PutMapping("/updateCost/{id}")
    public ResponseEntity<Map<String,Object>> updateCost(@RequestBody Cost cost,@PathVariable Long id){
        return costService.updateCost(cost,id);
    }

    @PostMapping("/saveCost/{messId}")
    public ResponseEntity<Map<String, Object>> addCost(@RequestBody Cost cost, @PathVariable String messId){
        return costService.addCost(cost,messId);
    }


    @GetMapping("getAllCost/{messId}/{year}/{month}")
    public ResponseEntity<Map<String,Object>> getAllCost(
            @PathVariable String messId,
            @PathVariable int year,
            @PathVariable int month

    ){
        return costService.getAllCost(messId,year,month);
    }

    @DeleteMapping("deleteCost/{id}")
    public ResponseEntity<Map<String ,Object>> deleteCost(@PathVariable Long id){
        return costService.deleteCost(id);
    }

}
