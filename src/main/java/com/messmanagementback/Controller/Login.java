package com.messmanagementback.Controller;
import com.messmanagementback.Model.Member;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Service.MessInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@Component
@CrossOrigin("http://localhost:3000")
public class Login {
    @Autowired
    private MessInfoService messInfoService;
    @Autowired
    public HttpServletRequest request;
    @GetMapping("/")
    public String local(){
        return "Hello world";
    }

    @GetMapping("/pppp")
    public List<Member> login(@RequestParam("messId") String messId, @RequestParam("messPassword") String messPassword){
        boolean ok = false;
        List<MessInfo> allMember = messInfoService.getAllMess();
        for(MessInfo member:allMember){
            if(Objects.equals(member.getMessId(), messId) && Objects.equals(member.getMessPassword(), messPassword)){
                ok = true;
                break;
            }
        }
        if(ok){
            HttpSession session = request.getSession();
            MessInfo loggedUser = messInfoService.findMess(messId);
            session.setAttribute("messId",messId);
            return loggedUser.getMembers();
        }
        else{
            return  null;
        }
    }

    @GetMapping("/logout")
    public String logout(){
        HttpSession session = request.getSession();
        session.invalidate();;
        return "your are logged out";
    }

}
