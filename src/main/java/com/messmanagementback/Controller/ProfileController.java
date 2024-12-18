package com.messmanagementback.Controller;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Model.Profile;
import com.messmanagementback.Service.MessInfoService;
import com.messmanagementback.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@Component
@CrossOrigin("http://localhost:3000")
public class ProfileController {
    @Autowired
    private MessInfoService messInfoService;

    @Autowired
    private ProfileService profileService;

    @PutMapping("/profile/{messId}")
    public ResponseEntity<Map<String, Object>> updateProfile(@PathVariable String messId, @RequestParam("profilePic") MultipartFile profilePic) {

        return profileService.updateProfile(profilePic,messId);
    }

    @GetMapping("/profile/{messId}")
    public ResponseEntity<Map<String ,Object>> getProfile(@PathVariable String messId){
        return profileService.getAProfile(messId);
    }


    @GetMapping("/currentMess")
    public String currentMess(){
        return null;
    }

}
