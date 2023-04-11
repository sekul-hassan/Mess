package com.messmanagementback.Controller;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Service.MessInfoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("http://localhost:3001")
public class Login {
    @Autowired
    private MessInfoService messInfoService;
    @Autowired
    private HttpSession session;

    @GetMapping("/login")
    public MessInfo login(@RequestBody MessInfo messInfo){
        boolean ok = false;
        List<MessInfo> allMember = messInfoService.getAllMess();
        String messId = messInfo.getMessId();
        String messPassword = messInfo.getMessPassword();
        for(MessInfo member:allMember){
            if(Objects.equals(member.getMessId(), messId) && Objects.equals(member.getMessPassword(), messPassword)){
                ok = true;
                break;
            }
        }
        if(ok){
            MessInfo loggedUser = messInfoService.findMess(messId);
            session.setAttribute("loggedUser",loggedUser.getMessId());
            return loggedUser;
        }
        else{
            return  null;
        }
    }
}
