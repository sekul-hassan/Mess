package com.messmanagementback.Controller;

import com.messmanagementback.Model.Member;
import com.messmanagementback.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
@CrossOrigin("http://localhost:3001")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/addMember")
    public Member addMember(@RequestBody Member member){
       return memberService.saveMember(member);
    }

    @GetMapping("/allMember")
    public List<Member> getAllMember(){
        return memberService.getAllMember();
    }

    @GetMapping("/oneMember/{id}")
    public Member getOne(@PathVariable Long id){
        return memberService.getOne(id);
    }

    @PutMapping("/oneMember/{id}")
    public Member updateMember(@RequestBody Member member, @PathVariable Long id){
        Member existingMember = memberService.getOne(id);
        if(member.getName()!=""){
            existingMember.setName(member.getName());
        }
        if(member.getEmail()!=""){
            existingMember.setEmail(member.getEmail());
        }
       if(member.getPhone()!=""){
           existingMember.setPhone(member.getPhone());
       }
       if(member.getAddTk()!=""){
           existingMember.setAddTk(member.getAddTk());
       }
       if(member.getTotalMeal()!=0.0){
           existingMember.setTotalMeal(member.getTotalMeal());
       }
        return memberService.saveMember(existingMember);
    }

    @DeleteMapping("/oneMember/{id}")
    public String deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
        return "Member is deleted";
    }

}
