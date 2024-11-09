package com.messmanagementback.Service;

import com.messmanagementback.Model.*;
import com.messmanagementback.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.Math.max;

@Service
public class CalculationService {

    @Autowired
    private MessInfoRepository messInfoRepository;
    @Autowired
    private CostRepository costRepository;
    @Autowired
    private ExtraBillRepository extraBillRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private SummaryRepository summaryRepository;


    public ResponseEntity<Map<String ,Object>> calculate(String messId, int month,int year){

        Map<String ,Object> response = new HashMap<>();

        Optional<MessInfo> messInfo = messInfoRepository.findById(messId);
        if(messInfo.isPresent()){
            MessInfo mess = messInfo.get();

            List<Member> members = mess.getMembers();

            Optional<List<Cost>> costs = costRepository.findAllByMessInfoAndYearAndMonth(mess,month,year);
            if(costs.isPresent()){
               List<Cost> costList = costs.get();

               Optional<List<ExtraBill>> extraBill = extraBillRepository.findAllByMessInfoAndYearAndMonth(mess,month,year);

               if(extraBill.isPresent()){
                   List<ExtraBill> extraBills = extraBill.get();
                   response.put("Members",members);
                   response.put("Costs",costList);
                   response.put("ExtraBill",extraBills);


                   double mealRate = 0.0,totalCost = 0.0,totalExtraBill = 0.0,totalMeal,fixedMeal = extraBills.get(0).getFixedMeal();

                   Map<Integer,Double> userDat = new HashMap<>();



                   return ResponseEntity.ok(response);
               }


            }
        }
        response.put("Something went wrong",null);
        return ResponseEntity.ok(response);

    }

}
