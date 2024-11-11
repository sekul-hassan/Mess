package com.messmanagementback.Repository;

import com.messmanagementback.Model.ExtraBill;
import com.messmanagementback.Model.MessInfo;
import com.messmanagementback.Model.Summary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SummaryRepository extends JpaRepository<Summary, Long> {
    @Query("SELECT c FROM Summary c WHERE c.messInfo = :messInfo AND YEAR(c.createdAt) = :year AND MONTH(c.createdAt) = :month")
    Optional<List<Summary>> findAllByMessInfoAndYearAndMonth(MessInfo messInfo, int year, int month);

}
