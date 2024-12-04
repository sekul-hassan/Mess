package com.messmanagementback.Controller;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Service.MemberService;
import com.messmanagementback.Service.MessInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Component
@CrossOrigin("http://localhost:3000")
public class MessInfoController {
    @Autowired
    private MessInfoService messInfoService;

    @PostMapping("/saveMess")
    public ResponseEntity<Map<String,Object>> saveMess(@RequestBody MessInfo messInfo) {
        return messInfoService.saveMess(messInfo);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> loginMess(@RequestHeader("messId")String messId , @RequestHeader("messPass")String messPass){
        System.out.println(messId + messPass);
        return messInfoService.loginMess(messId,messPass);
    }

    @GetMapping("/findMess/{id}")
    public ResponseEntity<Map<String,Object>> getMess(@PathVariable String id){
        return messInfoService.findMess(id);
    }

    @GetMapping("/findMess")
    public List<String> getAllMess(){
        return messInfoService.getAllMess();
    }

    @GetMapping("/calculateMealRate/{messId}/{fixedMeal}")
    public ResponseEntity<Map<String ,Object>> calculateMealRate(@PathVariable String messId,@PathVariable String fixedMeal){
        return messInfoService.calcMealRate(messId,fixedMeal);
    }

    @DeleteMapping("/findMess/{id}")
    public String deleteMess(@PathVariable String id){
        return messInfoService.deleteMess(id);
    }

}
