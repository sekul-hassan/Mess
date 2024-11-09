package com.messmanagementback.Repository;

import com.messmanagementback.Model.Cost;
import com.messmanagementback.Model.MessInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CostRepository extends JpaRepository<Cost, Long> {

    @Query("SELECT c FROM Cost c WHERE c.messInfo = :messInfo AND YEAR(c.createdAt) = :year AND MONTH(c.createdAt) = :month")
    Optional<List<Cost>> findAllByMessInfoAndYearAndMonth(MessInfo messInfo, int year, int month);

}
