package com.messmanagementback.Repository;

import com.messmanagementback.Model.ExtraBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraBillRepository extends JpaRepository<ExtraBill,Long> {
}
