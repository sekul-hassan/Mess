package com.messmanagementback.Repository;

import com.messmanagementback.Model.MessInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessInfoRepository extends JpaRepository<MessInfo,String> {
}
