package com.messmanagementback.Service;

import com.messmanagementback.Model.Member;
import com.messmanagementback.Repository.MemberRipository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberService{
    @Autowired
    private MemberRipository memberRipository;
    public Member saveMember(Member member){
       return memberRipository.save(member);
    }
    public List<Member> getAllMember(){
        return memberRipository.findAll();
    }
    public Member getOne(Long id){
       return memberRipository.findById(id).get();
    }
    public void deleteMember(Long id){
        memberRipository.deleteById(id);
    }
}
