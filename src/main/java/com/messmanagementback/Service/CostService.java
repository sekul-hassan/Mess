package com.messmanagementback.Service;
import com.messmanagementback.Model.Cost;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Repository.CostRepository;
import com.messmanagementback.Repository.MessInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class CostService {

    @Autowired
    private CostRepository costRepository;

    @Autowired
    private MessInfoRepository messInfoRepository;

    // This is completed
    public ResponseEntity<Map<String,Object>> updateCost(Cost cost,Long id){
        Map<String,Object> response = new HashMap<>();

       try {
           Optional<Cost> cost1 = costRepository.findById(id);
           if(cost1.isPresent()){
               Cost cost2 = cost1.get();
               cost2.setBill(cost.getBill());
               Cost updateCost = costRepository.save(cost2);
               response.put("status",HttpStatus.OK.value());
               response.put("cost",updateCost);
               response.put("message","Cost updated successfully");
               return ResponseEntity.status(HttpStatus.OK.value()).body(response);
           }
           response.put("status",HttpStatus.NOT_FOUND.value());
           response.put("message","Cost not found");
           return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);
       }catch (Exception e){
           response.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
           response.put("message",e.getMessage());
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
       }
    }

    // This is completed
    public ResponseEntity<Map<String, Object>> addCost(Cost cost, String messId) {
        Map<String, Object> response = new HashMap<>();
        LocalDate currentDate = LocalDate.now();

       try {
           Date billDate = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
           Optional<MessInfo> messInfoOptional = messInfoRepository.findById(messId);

           if (messInfoOptional.isPresent()) {
               MessInfo messInfo = messInfoOptional.get();

               cost.setMessInfo(messInfo);
               cost.setBillDate(billDate);

               Cost savedCost = costRepository.save(cost);

               response.put("status", HttpStatus.CREATED.value());
               response.put("message", "Cost added successfully");
               response.put("data", savedCost);

               return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);
           } else {
               response.put("status", HttpStatus.NOT_FOUND.value());
               response.put("message", "MessInfo not found with ID: " + messId);

               return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);
           }
       }catch (Exception e){
           response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
           response.put("message", e.getMessage());
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
       }
    }

    // This is completed
    public ResponseEntity<Map<String,Object>> getAllCost(String messId,int year,int month){

        Map<String, Object> response = new HashMap<>();

        try {
            Optional<MessInfo> messInfo = messInfoRepository.findById(messId);
            if (messInfo.isPresent()) {
                MessInfo messInfo1 = messInfo.get();
                Optional<List<Cost>> costs = costRepository.findAllByMessInfoAndYearAndMonth(messInfo1,year,month);

                if (costs.isPresent()) {
                    response.put("status", HttpStatus.OK.value());
                    response.put("message", "Costs found");
                    response.put("data", costs);
                    return ResponseEntity.status(HttpStatus.OK.value()).body(response);
                }

                response.put("status", HttpStatus.NOT_FOUND.value());
                response.put("message", "cost not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);
            }
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "MessInfo not found with ID: " + messId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);

        }catch (Exception e){
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
        }
    }

    // This is completed
    public ResponseEntity<Map<String,Object>> deleteCost(Long id){
       Map<String, Object> response = new HashMap<>();

       try {
           Optional<Cost> cost1 = costRepository.findById(id);
           if(cost1.isPresent()){
               Cost cost = cost1.get();
               costRepository.delete(cost);
               response.put("status", HttpStatus.OK.value());
               response.put("message", "Cost deleted successfully");
               return ResponseEntity.status(HttpStatus.OK.value()).body(response);
           }else{
               response.put("status", HttpStatus.NOT_FOUND.value());
               response.put("message", "Cost not found");
               return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);
           }
       }catch (Exception e){
           response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
           response.put("message", e.getMessage());
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
       }
    }

}
