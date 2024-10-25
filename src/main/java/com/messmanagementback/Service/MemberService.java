package com.messmanagementback.Service;

import com.messmanagementback.Model.Member;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberService{
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MessInfoService messInfoService;

    // This is completed
    public ResponseEntity<Map<String,Object>> addMember(Member member, String messId){

        Map<String,Object> response = new HashMap<>();

        try {
            MessInfo messInfo = messInfoService.findMess(messId);
            if(Objects.isNull(messInfo)){
                response.put("status", HttpStatus.NOT_FOUND.value());
                response.put("message", "Mess does not exist");
            }
            else{
                member.setMessInfo(messInfo);
                memberRepository.save(member);
                response.put("status", HttpStatus.CREATED.value());
                response.put("message", "Member added successful");
                response.put("data",member);
            }

           return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // This is completed
    public ResponseEntity<Map<String,Object>> getAllMember(String messId){
        Map<String,Object> response = new HashMap<>();

        try {
            MessInfo messInfo = messInfoService.findMess(messId);
            if(Objects.isNull(messInfo)){
                response.put("status", HttpStatus.NOT_FOUND.value());
                response.put("message", "Illegal Access");
            }
            else{
                response.put("status", HttpStatus.OK.value());
                response.put("message", "Member found");
                response.put("data",messInfo.getMembers());
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", "Illegal Access");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    // This is completed
    public ResponseEntity<Map<String, Object>> updateMember(Member member, String messId, Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            MessInfo messInfo = messInfoService.findMess(messId);
            List<Member> members = messInfo.getMembers();
            boolean ok = false;

            for (Member member1 : members) {
                if (member1.getId().equals(id)) {
                    member.setId(id);
                    member.setMessInfo(messInfo);

                    if (Objects.equals(member.getEmail(), "")) {
                        member.setEmail(member1.getEmail());
                    }
                    if (Objects.equals(member.getName(), "")) {
                        member.setName(member1.getName());
                    }
                    if (Objects.equals(member.getPhone(), "")) {
                        member.setPhone(member1.getPhone());
                    }
                    if (member.getAddTk()==0.0) {
                        member.setAddTk(member1.getAddTk());
                    }
                    if((member.getTotalMeal() ==0.0)){
                        member.setTotalMeal(member1.getTotalMeal());
                    }

                    ok = true;
                    break;
                }
            }

            if (!ok) {
                response.put("status", HttpStatus.NOT_FOUND.value());
                response.put("message", "User does not exist");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {
                memberRepository.save(member);

                response.put("status", HttpStatus.OK.value());
                response.put("message", "Member updated successfully");
                response.put("data", member);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
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
        memberRepository.deleteById(id);
    }


}
