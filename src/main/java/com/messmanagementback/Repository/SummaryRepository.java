package com.messmanagementback.Repository;

import com.messmanagementback.Model.Summary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummaryRepository extends JpaRepository<Summary,Long> {
}
