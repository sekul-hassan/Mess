package com.messmanagementback.Controller;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Service.MemberService;
import com.messmanagementback.Service.MessInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
@CrossOrigin("http://localhost:3000")
public class MessInfoController {
    @Autowired
    private MessInfoService messInfoService;

    @PostMapping("/saveMess")
    public ResponseEntity<MessInfo> saveMess(@RequestBody MessInfo messInfo) {
        try {
            // Call the service method to save the mess info
            MessInfo savedMessInfo = messInfoService.saveMess(messInfo);

            // Return the saved MessInfo object with HTTP status 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMessInfo);

        } catch (Exception e) {
            // In case of an exception, return a bad request (400) or other relevant status code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/findMess/{id}")
    public MessInfo getMess(@PathVariable String id){
        return messInfoService.findMess(id);
    }
    @GetMapping("findMess")
    public List<MessInfo> getAllMess(){
        return messInfoService.getAllMess();
    }

    @PutMapping("/findMess/{id}")
    public MessInfo updateMess(@RequestBody MessInfo messInfo,@PathVariable String id){
        MessInfo existing = messInfoService.findMess(id);
        if(messInfo.getMessEmail()!=null){
            existing.setMessEmail(messInfo.getMessEmail());
        }
        if(messInfo.getMessName()!=null){
            existing.setMessName(messInfo.getMessName());
        }
        if(messInfo.getMessPassword()!=null){
            existing.setMessPassword(messInfo.getMessPassword());
        }
        return messInfoService.saveMess(existing);
    }
    @DeleteMapping("/findMess/{id}")
    public String deleteMess(@PathVariable String id){
        return messInfoService.deleteMess(id);
    }


    //// Here mapping testing



}
