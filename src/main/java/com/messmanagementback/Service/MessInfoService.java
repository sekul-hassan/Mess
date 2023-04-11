package com.messmanagementback.Service;

import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Repository.MessInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessInfoService {
    @Autowired
    private MessInfoRepository messInfoRepository;

    public List<MessInfo> getAllMess(){
        return messInfoRepository.findAll();
    }
    public MessInfo findMess(String messId){
        return messInfoRepository.findById(messId).get();
    }
    public MessInfo saveMess(MessInfo messInfo){
        return messInfoRepository.save(messInfo);
    }
    public String deleteMess(String id){
        messInfoRepository.deleteById(id);
        return "delete done";
    }
}
