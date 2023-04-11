package com.messmanagementback.Controller;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Service.MessInfoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
@CrossOrigin("http://localhost:3001")
public class Profile {
    @Autowired
    private MessInfoService messInfoService;
    @Autowired
    private HttpSession session;

    @GetMapping("/profile")
    public MessInfo getProfile(){
        String loggedUserId = (String) session.getAttribute("loggedUser");
        return messInfoService.findMess(loggedUserId);
    }

}
