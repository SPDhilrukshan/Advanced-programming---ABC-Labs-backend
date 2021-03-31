package com.icbt.advancedprogramming.advancedprogramming.repository;

import com.icbt.advancedprogramming.advancedprogramming.model.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BillingRepositroy extends JpaRepository<Billing,Long> {

    Billing findByBillingId(Long billingId);
    List<Billing> findAllByBilledDateBetween(Date startDate, Date endDate);
}
