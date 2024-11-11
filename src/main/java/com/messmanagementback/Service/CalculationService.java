package com.messmanagementback.Service;

import com.messmanagementback.Model.*;
import com.messmanagementback.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

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


    public ResponseEntity<Map<String ,Object>> calculate(String messId, int year,int month){

        Map<String ,Object> response = new HashMap<>();

        Optional<MessInfo> messInfo = messInfoRepository.findById(messId);
        if(messInfo.isPresent()){
            MessInfo mess = messInfo.get();

            List<Member> members = mess.getMembers();

            Optional<List<Cost>> costs = costRepository.findAllByMessInfoAndYearAndMonth(mess,year,month);
            if(costs.isPresent()){
               List<Cost> costList = costs.get();

               Optional<List<ExtraBill>> extraBill = extraBillRepository.findAllByMessInfoAndYearAndMonth(mess,year,month);

               if(extraBill.isPresent()){
                   List<ExtraBill> extraBills = extraBill.get();

                   ExtraBill extraBill1 = extraBills.get(0);


                   double mealRate,totalTk = 0.0,totalCost = 0.0,totalExtraBill = 0.0,totalMeal = 0.0,fixedMeal = extraBill1.getFixedMeal(),extraBillPerPerson;
                   int totalMember = members.size();
                   Map<Integer,Double> userData = new HashMap<>();

                   for(Member member : members){
                       totalMeal += max(fixedMeal,member.getTotalMeal());
                       totalTk += member.getAddTk();
                   }

                   for(Cost cost : costList){
                       totalCost += cost.getBill();
                   }

                   totalExtraBill += extraBill1.getKhalaBill() + extraBill1.getWifiBill() + extraBill1.getOthers();

                   mealRate = totalCost/totalMeal ;

                   extraBillPerPerson = totalExtraBill / totalMember;

                   for(Member member : members){
                       double tempCost = member.getTotalMeal()*mealRate + extraBillPerPerson;
                       member.setBackTk(member.getAddTk() - tempCost);
                       memberRepository.save(member);
                   }

                   Optional<List<Summary>> optionalSummary = summaryRepository.findAllByMessInfoAndYearAndMonth(mess, year, month);
                   if (optionalSummary.isPresent()) {
                       List<Summary> summaryList = optionalSummary.get();
                       if(!summaryList.isEmpty()){
                           System.out.println("Summary already exist");
                           Summary summary = optionalSummary.get().get(0);
                           System.out.println(summary.getTotalMeal());
                           System.out.println("From");
                           summary.setId(summary.getId());
                           summary.setAddTk(totalTk);
                           summary.setTotalMeal(totalMeal);
                           summary.setTotalCost(totalCost);
                           summary.setMealRate(mealRate);
                           summary.setMessInfo(mess);
                           summaryRepository.save(summary);
                           response.put("summary",summary);
                           return ResponseEntity.ok(response);
                       }
                       else{
                           Summary summary = new Summary();
                           System.out.println("Summary not exist");
                           summary.setAddTk(totalTk);
                           summary.setTotalMeal(totalMeal);
                           summary.setTotalCost(totalCost);
                           summary.setMealRate(mealRate);
                           summary.setMessInfo(mess);
                           summaryRepository.save(summary);
                           response.put("summary",summary);
                       }
                   }

                   }

               }


            }
        return null;
    }

}
