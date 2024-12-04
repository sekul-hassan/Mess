package com.messmanagementback.Service;

import com.messmanagementback.Model.Cost;
import com.messmanagementback.Model.ExtraBill;
import com.messmanagementback.Model.Member;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Repository.CostRepository;
import com.messmanagementback.Repository.MessInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessInfoService {
    @Autowired
    private MessInfoRepository messInfoRepository;
    @Autowired
    private ExtraBillService extraBillService;
    @Autowired
    private CostRepository costRepository;

    public List<String> getAllMess(){
        ArrayList<String> data = new ArrayList<>();
        List<MessInfo> messInfoList = messInfoRepository.findAll();

        for(MessInfo messInfo : messInfoList){
            data.add(messInfo.getMessId());
            data.add(messInfo.getMessEmail());
        }

        return data;
    }

    public ResponseEntity<Map<String ,Object>> findMess(String messId) {
        Map<String ,Object> response = new HashMap<>();

        Optional<MessInfo> messInfo = messInfoRepository.findById(messId);
        if(messInfo.isPresent()){
            response.put("message","Mess info found");
            response.put("data", messInfo.get());
            response.put("status", HttpStatus.OK);
        }else{
            response.put("message","Mess info not found");
            response.put("status", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, Object>> saveMess(MessInfo messInfo){

        Map<String,Object> response = new HashMap<>();

        try {
            // Call the service method to save the mess info
            Optional<MessInfo> existingMessInfo = messInfoRepository.findByMessEmail(messInfo.getMessEmail());
            Optional<MessInfo> existingMessInfo2 = messInfoRepository.findById(messInfo.getMessId());
            if(existingMessInfo.isPresent()) {
                response.put("status", HttpStatus.CONFLICT.value());
                response.put("message", "Email Already Exists");
                return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(response);
            }
            else if(existingMessInfo2.isPresent()) {
                response.put("status", HttpStatus.CONFLICT.value());
                response.put("message", "Mess ID Already Exists");
                return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(response);
            }
            MessInfo savedMessInfo = messInfoRepository.save(messInfo);
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "Mess Saved");
            response.put("data", savedMessInfo);

            // Return the saved MessInfo object with HTTP status 201 Created
            return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);

        } catch (Exception e) {
            response.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error",e.getMessage());
            response.put("message","Failed to save mess");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    public ResponseEntity<Map<String, Object>> loginMess(String messId, String password) {
        Map<String, Object> response = new HashMap<>();
        System.out.println(messId + " " + password);
        try {
            // Combine the repository queries to check both MessId and MessEmail at once
            Optional<MessInfo> messInfo = messInfoRepository.findByMessIdOrMessEmailAndMessPassword(messId, messId, password);

            if (messInfo.isPresent()) {
                response.put("status", HttpStatus.OK.value());
                response.put("message", "Mess Login Successful");
                response.put("data", messInfo.get());
            } else {
                response.put("status", HttpStatus.NOT_FOUND.value());
                response.put("message", "Mess not found.");
            }

        } catch (Exception e) {
            // Error handling
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", e.getMessage());
            response.put("message", "Internal Server Error.");
        }

        // Return the built response at the end
        return ResponseEntity.status((int) response.get("status")).body(response);
    }

    public ResponseEntity<Map<String ,Object>> calcMealRate(String messId, String mealRate1) {
        Map<String ,Object> response = new HashMap<>();
        try {
            Long mealRate = Long.parseLong(mealRate1);
            Optional<MessInfo> messInfo = messInfoRepository.findById(messId);
            if(messInfo.isPresent()) {
                MessInfo messInfo1 = messInfo.get();
                List<Member> members = messInfo1.getMembers();
                
            }
        }catch (Exception e){
            response.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return null;
    }


    @Transactional
    public void saveExtraWithByMessInfo(MessInfo messInfo){
//        MessInfo messInfo1 = messInfoRepository.save(messInfo);
//        String id = messInfo1.getMessId();
//        ExtraBill extraBill = new ExtraBill();
//        extraBill.setMessId(id);
//        extraBillService.saveBill(extraBill);
//        Cost cost = new Cost();
//        cost.setMessInfo(messInfo1);
//        costRepository.save(cost);
        return ;
    }
    public String deleteMess(String id){
        messInfoRepository.deleteById(id);
        return "delete done";
    }
}
