package com.messmanagementback.Service;
import com.messmanagementback.Model.ExtraBill;
import com.messmanagementback.Repository.ExtraBillRipository;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraBillService {
   @Resource
   private ExtraBillRipository extraBillRipository;
   public ExtraBill saveBill(ExtraBill extraBill){
       return extraBillRipository.save(extraBill);
   }
   public Optional<ExtraBill> getMess(String id){
       return extraBillRipository.findById(id);
   }

   public String  deleteMess(String id){
       extraBillRipository.deleteById(id);
       return "Delete successfully";
   }
}
