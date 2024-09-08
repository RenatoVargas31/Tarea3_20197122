package com.app.tarea3_20197122.repository;

import com.app.tarea3_20197122.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByDepartmentId(Integer departmentId);
}
