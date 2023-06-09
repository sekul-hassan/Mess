package com.messmanagementback.Service;

import com.messmanagementback.Model.Cost;
import com.messmanagementback.Model.ExtraBill;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Repository.CostRepository;
import com.messmanagementback.Repository.MessInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessInfoService {
    @Autowired
    private MessInfoRepository messInfoRepository;
    @Autowired
    private ExtraBillService extraBillService;
    @Autowired
    private CostRepository costRepository;

    public List<MessInfo> getAllMess(){
        return messInfoRepository.findAll();
    }
    public MessInfo findMess(String messId){
        return messInfoRepository.findById(messId).get();
    }
    public MessInfo saveMess(MessInfo messInfo){
        return messInfoRepository.save(messInfo);
    }
    @Transactional
    public void saveExtraWithByMessInfo(MessInfo messInfo){
        MessInfo messInfo1 = messInfoRepository.save(messInfo);
        String id = messInfo1.getMessId();
        ExtraBill extraBill = new ExtraBill();
        extraBill.setMessId(id);
        extraBillService.saveBill(extraBill);
        Cost cost = new Cost();
        cost.setMessInfo(messInfo1);
        costRepository.save(cost);
    }
    public String deleteMess(String id){
        messInfoRepository.deleteById(id);
        return "delete done";
    }
}
