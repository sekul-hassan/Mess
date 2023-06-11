package com.messmanagementback.Repository;

import com.messmanagementback.Model.Cost;
import com.messmanagementback.Model.MessInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostRepository extends JpaRepository<Cost,Long> {

    public Cost findCostByMessInfo(MessInfo messInfo);
    public List<Cost> findAllByMessInfoAndYearAndMonth(MessInfo messInfo,int year,int month);
}
