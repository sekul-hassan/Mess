package com.messmanagementback.Service;
import com.messmanagementback.Model.Cost;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Repository.CostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

@Service
public class CostService {

    @Autowired
    private CostRepository costRepository;
    @Autowired
    private MessInfoService messInfoService;

    public Cost saveCost(Cost cost,Long id){
        Cost cost1 = costRepository.findById(id).get();
        if(cost.getBill()!=0.0){
            cost1.setBill(cost.getBill());
        }
        return costRepository.save(cost1);
    }

    public Cost addCost(Cost cost,String messId){
        LocalDate currentDate = LocalDate.now();
        MessInfo messInfo = messInfoService.findMess(messId);
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        int day = currentDate.getDayOfMonth();
        cost.setMessInfo(messInfo);
        cost.setYear(year);
        cost.setMonth(month);
        cost.setDay(day);
        return costRepository.save(cost);
    }

    public List<Cost> getAllCost(String messId){
        MessInfo messInfo = messInfoService.findMess(messId);
        return messInfo.getCosts();
    }

    public Cost deleteCost(Long id){
        Cost cost = costRepository.findById(id).get();
        costRepository.deleteById(id);
        return cost;
    }

}
