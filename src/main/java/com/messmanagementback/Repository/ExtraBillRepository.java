package com.messmanagementback.Repository;

import com.messmanagementback.Model.Cost;
import com.messmanagementback.Model.ExtraBill;
import com.messmanagementback.Model.MessInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExtraBillRepository extends JpaRepository<ExtraBill,Long> {

    @Query("SELECT c FROM ExtraBill c WHERE c.messInfo = :messInfo AND YEAR(c.createdAt) = :year AND MONTH(c.createdAt) = :month")
    Optional<List<ExtraBill>> findAllByMessInfoAndYearAndMonth(MessInfo messInfo, int year, int month);

    Optional<List<ExtraBill>> findAllByMessInfo(MessInfo messInfo);

}
