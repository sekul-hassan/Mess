package com.messmanagementback.Repository;

import com.messmanagementback.Model.Member;
import com.messmanagementback.Service.MemberService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRipository extends JpaRepository<Member,Long> {

}
