package com.messmanagementback.Controller;

import com.messmanagementback.Model.Member;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
@CrossOrigin("http://localhost:3000")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private  HttpSession session ;
    @PostMapping("/addMember/{messId}")
    public Member addMember(@RequestBody Member member,@PathVariable String messId){
        return memberService.addMember(member,messId);
    }

    @GetMapping("/allMember/{messId}")
    public List<Member> getAllMember(@PathVariable String messId){
        return memberService.getAllMember(messId);
    }

    @GetMapping("/oneMember/{messId}/{id}")
    public Member getOne(@PathVariable String messId,@PathVariable Long id){
        return memberService.getOne(messId,id);
    }

    @PutMapping("/oneMember/{messId}/{id}")
    public Member updateMember(@RequestBody Member member,@PathVariable String messId ,@PathVariable Long id){

        return memberService.updateMember(member,messId,id);
    }

    @DeleteMapping("/oneMember/{id}")
    public String deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
        return "Member is deleted";
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
