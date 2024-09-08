package com.app.tarea3_20197122.repository;

import com.app.tarea3_20197122.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmployeeId(Integer id);
}
