package com.messmanagementback.Service;

import com.messmanagementback.Model.*;
import com.messmanagementback.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

import static java.lang.Math.max;

@Service
public class CalculationService {

    @Autowired
    private MessInfoRepository messInfoRepository;
    @Autowired
    private CostRepository costRepository;
    @Autowired
    private ExtraBillRipository extraBillRipository;
    @Autowired
    private MemberRipository memberRepository;
    @Autowired
    private SummaryRepository summaryRepository;

    DecimalFormat df = new DecimalFormat("#.##");
    public Summary calculate(String messId){
        MessInfo messInfo = messInfoRepository.findById(messId).get();
        List<Member> members = messInfo.getMembers();

        ExtraBill extraBill = extraBillRipository.findById(messId).get();
        LocalDate localDate = LocalDate.now();

        List<Cost> costs = costRepository.findAllByMessInfoAndYearAndMonth(messInfo,localDate.getYear(),localDate.getMonthValue());

        long totalTk=0L;
        double totalMeal = 0.0;
        double mealRate = 0.0;
        long totalCost = 0L;
        double fixed = max(0,extraBill.getFixedMeal());
        double khala = max(0,extraBill.getKhalaBill());
        double wifi = max(0,extraBill.getWifiBill());

        long totalMember = members.size();
        double others = khala;



        for (Member member : members) {
            Double totalMealValue = member.getTotalMeal();
            if(totalMealValue==null){
                totalMealValue = 0.0;
            }
            member.setTotalMeal(max(fixed, totalMealValue));
            String tk = member.getAddTk();
            if(tk==null) tk = "0";
            totalTk += Long.parseLong(tk);
            totalMeal += member.getTotalMeal();
        }


        for(Cost cost:costs){
            totalCost += max(0,cost.getBill());
        }

        mealRate = (totalCost*1.0)/(totalMeal);


        others += wifi /(1.0*totalMember);

        for(Member member:members){
            others += (1.0*member.getTotalMeal())*mealRate;
            long getTK = Long.parseLong(member.getAddTk());
            double remain = getTK-others;
            String value = df.format(remain);
            member.setBackTk(value);
            memberRepository.save(member);
        }


        Summary summary1 = summaryRepository.findById(messId).orElse(null);
        Summary summary = new Summary();
        if(summary1==null){
            summary.setMessId(messId);
            summary.setFixed(max(0,fixed));
            summary.setKhala(max(0,khala));
            summary.setWifi(max(0,wifi));
            summary.setAddTk(max(0,totalTk));
            summary.setTotalCost(max(0,totalCost));
            summary.setMealRate(max(0,mealRate));
            summary.setTotalMeal(max(0,totalMeal));
            return summaryRepository.save(summary);
        }
        else{
            summary1.setMessId(messId);
            summary1.setFixed(max(0,fixed));
            summary1.setKhala(max(0,khala));
            summary1.setWifi(max(0,wifi));
            summary1.setAddTk(max(0,totalTk));
            summary1.setTotalCost(max(0,totalCost));
            summary1.setMealRate(max(0,mealRate));
            summary1.setTotalMeal(max(0,totalMeal));
            return summaryRepository.save(summary1);
        }
    }
}
