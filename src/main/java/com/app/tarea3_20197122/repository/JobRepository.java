package com.app.tarea3_20197122.repository;

import com.app.tarea3_20197122.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {
    Job findByJobId(String id);
}
