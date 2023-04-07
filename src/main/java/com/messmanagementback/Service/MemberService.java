package com.messmanagementback.Service;

import com.messmanagementback.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface MemberService extends JpaRepository<Member,Long> {
}
