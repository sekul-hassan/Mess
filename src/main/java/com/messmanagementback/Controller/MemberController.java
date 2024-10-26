package com.messmanagementback.Controller;

import com.messmanagementback.Model.Member;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Component
@CrossOrigin("http://localhost:3000")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private  HttpSession session ;

    @PostMapping("/addMember/{messId}")
    public ResponseEntity<Map<String,Object>> addMember(@RequestBody Member member, @PathVariable String messId){
        return memberService.addMember(member,messId);
    }

    @GetMapping("/allMember/{messId}")
    public ResponseEntity<Map<String,Object>> getAllMember(@PathVariable String messId){
        return memberService.getAllMember(messId);
    }

    @GetMapping("/oneMember/{messId}/{id}")
    public Member getOne(@PathVariable String messId,@PathVariable Long id){
        return memberService.getOne(messId,id);
    }

    @PutMapping("/oneMember/{messId}/{id}")
    public ResponseEntity<Map<String,Object>> updateMember(@RequestBody Member member,@PathVariable String messId ,@PathVariable Long id){

        return memberService.updateMember(member,messId,id);
    }

    @DeleteMapping("/oneMember/{id}")
    public ResponseEntity<Map<String,Object>> deleteMember(@PathVariable Long id){
        return memberService.deleteMember(id);
    }

    @GetMapping("/checkSession")
    public String  checkSession(){
        String user = (String) session.getAttribute("messId");
        if(user==null){
            return "session not found";
        }
        return "session found";
    }

}
