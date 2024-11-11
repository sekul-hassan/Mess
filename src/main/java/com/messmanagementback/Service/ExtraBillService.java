package com.messmanagementback.Service;
import com.messmanagementback.Model.ExtraBill;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Repository.ExtraBillRepository;
import com.messmanagementback.Repository.MessInfoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraBillService {
   @Autowired
   private ExtraBillRepository extraBillRepository;

   @Autowired
   private MessInfoRepository messInfoRepository;

   public ResponseEntity<Map<String,Object>> saveBill(ExtraBill extraBill, String messId){

       Date date = new Date();
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(date);

       int year = calendar.get(Calendar.YEAR);
       int month = calendar.get(Calendar.MONTH) + 1;



       Map<String,Object> response = new HashMap<>();
       try {
           Optional<MessInfo> messInfo = messInfoRepository.findById(messId);
           if (messInfo.isPresent()) {
               MessInfo messInfo1 = messInfo.get();
               Optional<List<ExtraBill>> extraBillOptional = extraBillRepository.findAllByMessInfoAndYearAndMonth(messInfo1,year,month);
               if (extraBillOptional.isPresent()) {
                   response.put("status", HttpStatus.CONFLICT);
                   response.put("message","You can not add new extra bill to this month.\nYou can modify this.");
                   return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
               }
               extraBill.setMessInfo(messInfo1);
               ExtraBill extraBill1 = extraBillRepository.save(extraBill);
               response.put("status", HttpStatus.CREATED.value());
               response.put("message","ExtraBill added successful.");
               response.put("data", extraBill1);
               return ResponseEntity.status(HttpStatus.CREATED).body(response);
           }
           response.put("status", HttpStatus.NOT_FOUND.value());
           response.put("message","This mess does not found.");
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
       }catch (Exception e){
          response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
          response.put("message", e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
       }
   }

   public ResponseEntity<Map<String ,Object>> findExtraBill(String messId){
      Map<String ,Object> response = new HashMap<>();

      try {
          Optional<MessInfo> messInfo = messInfoRepository.findById(messId);
          if (messInfo.isPresent()) {
              MessInfo messInfo1 = messInfo.get();
              List<ExtraBill> extraBills = messInfo1.getExtraBills();
              response.put("status", HttpStatus.OK.value());
              response.put("message","ExtraBill found successful.");
              response.put("data", extraBills);
              return ResponseEntity.status(HttpStatus.OK).body(response);
          }
          response.put("status", HttpStatus.NOT_FOUND.value());
          response.put("message","This mess does not found.");
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
      }catch (Exception e){
          response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
          response.put("message", e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
      }
   }

    public ResponseEntity<Map<String, Object>> updateBill(ExtraBill extraBill, Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<ExtraBill> extraBillOptional = extraBillRepository.findById(id);
            if (extraBillOptional.isPresent()) {
                ExtraBill existingBill = extraBillOptional.get();

                extraBill.setId(existingBill.getId());
                extraBill.setMessInfo(existingBill.getMessInfo());

                if (extraBill.getWifiBill() == null || extraBill.getWifiBill().equals(0.0)) {
                    extraBill.setWifiBill(existingBill.getWifiBill());
                }
                if (extraBill.getKhalaBill() == null || extraBill.getKhalaBill().equals(0.0)) {
                    extraBill.setKhalaBill(existingBill.getKhalaBill());
                }
                if (extraBill.getOthers() == null || extraBill.getOthers().equals(0.0)) {
                    extraBill.setOthers(existingBill.getOthers());
                }

                extraBillRepository.save(extraBill);
                response.put("status", HttpStatus.OK.value());
                response.put("message", "ExtraBill updated successfully.");
                response.put("data", extraBill);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "This bill was not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    public ResponseEntity<Map<String ,Object>> deleteExtraBill(Long id){
       Map<String ,Object> response = new HashMap<>();

       try {
           Optional<ExtraBill> extraBill = extraBillRepository.findById(id);
           if (extraBill.isPresent()) {
               ExtraBill extraBill1 = extraBill.get();
               extraBillRepository.delete(extraBill1);
               response.put("status", HttpStatus.OK.value());
               response.put("message","ExtraBill deleted successful.");
               response.put("data", extraBill1);
               return ResponseEntity.status(HttpStatus.OK).body(response);
           }
           response.put("status", HttpStatus.NOT_FOUND.value());
           response.put("message","This bill does not found.");
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
       }catch (Exception e){
           response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
           response.put("message", e.getMessage());
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
       }
    }

}
