package com.app.tarea3_20197122.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "department_id")
    private Integer departmentId;
    @Column(name = "job_id")
    private String jobId;
    @Transient
    private Department department;
    @Transient
    private Job job;
}
