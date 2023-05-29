package com.messmanagementback.Service;
import com.messmanagementback.Model.ExtraBill;
import com.messmanagementback.Repository.ExtraBillRipository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraBillService {
   @Autowired
   private ExtraBillRipository extraBillRipository;
   public ExtraBill saveBill(ExtraBill extraBill){
       return extraBillRipository.save(extraBill);
   }

}
