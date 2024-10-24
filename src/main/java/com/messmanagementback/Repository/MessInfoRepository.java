package com.messmanagementback.Repository;

import com.messmanagementback.Model.MessInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessInfoRepository extends JpaRepository<MessInfo,String> {

    Optional<MessInfo> findByMessEmail(String messEmail);

    Optional<MessInfo> findByMessIdOrMessEmailAndMessPassword(String messId, String messEmail, String messPassword);

}
