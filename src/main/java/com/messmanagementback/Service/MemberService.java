package com.messmanagementback.Service;

import com.messmanagementback.Model.Member;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Repository.MemberRipository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberService{
    @Autowired
    private MemberRipository memberRipository;

    @Autowired
    private MessInfoService messInfoService;
    public Member addMember(Member member,String messId){
        MessInfo messInfo = messInfoService.findMess(messId);
        member.setMessInfo(messInfo);
        return memberRipository.save(member);
    }
    public List<Member> getAllMember(String messId){
       MessInfo messInfo = messInfoService.findMess(messId);
       return messInfo.getMembers();
    }

    public Member updateMember(Member member,String messId,Long id){
        MessInfo messInfo = messInfoService.findMess(messId);
        List<Member> members = messInfo.getMembers();
        for(Member member1:members){
            if(member1.getId().equals(id)){
                member.setId(id);
                member.setMessInfo(messInfo);
                if(Objects.equals(member.getEmail(), ""))member.setEmail(member1.getEmail());
                if(Objects.equals(member.getName(), ""))member.setName(member1.getName());
                if(Objects.equals(member.getPhone(), ""))member.setPhone(member1.getPhone());
                if(Objects.equals(member.getAddTk(), ""))member.setAddTk(member1.getAddTk());
                if(member.getTotalMeal()==0.0)member.setTotalMeal(member1.getTotalMeal());
                return memberRipository.save(member);
            }
        }
        return null;
    }

    public Member getOne(String messId,Long id){
        MessInfo messInfo = messInfoService.findMess(messId);
        List<Member> members = messInfo.getMembers();
        for(Member member:members){
            if(member.getId().equals(id)){
                return member;
            }
        }

       return null;
    }
    public void deleteMember(Long id){
        memberRipository.deleteById(id);
    }


}
