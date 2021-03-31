package com.icbt.advancedprogramming.advancedprogramming.repository;

import com.icbt.advancedprogramming.advancedprogramming.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Employee findByUserId(Long userId);

    Employee findByUserName(String username);
}
