package com.messmanagementback.Service;

import com.messmanagementback.Repository.ExtraBillRipository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtraBillService {
    @Autowired
    ExtraBillRipository extraBillRipository;
}
