package com.messmanagementback.Repository;

import com.messmanagementback.Model.MessInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MessInfoRepository extends JpaRepository<MessInfo,String> {

    Optional<MessInfo> findByMessEmail(String messEmail);

    Optional<MessInfo> findByMessIdOrMessEmailAndMessPassword(String messId, String messEmail, String messPassword);

    @Query("SELECT m FROM MessInfo m WHERE m.messId = :messId AND YEAR(m.createdAt) = :year AND MONTH(m.createdAt) = :month")
    Optional<MessInfo> findByMessIdAndYearAndMonth(
            @Param("messId") String messId,
            @Param("year") int year,
            @Param("month") int month
    );


}
