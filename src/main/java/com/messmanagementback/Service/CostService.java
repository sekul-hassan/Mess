package com.messmanagementback.Service;
import com.messmanagementback.Model.Cost;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Repository.CostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.YearMonth;
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
        YearMonth yearMonth = YearMonth.now();
        MessInfo messInfo = messInfoService.findMess(messId);
        cost.setMessInfo(messInfo);
        cost.setMonthYear(yearMonth);
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
