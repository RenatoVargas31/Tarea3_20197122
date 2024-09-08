package com.app.tarea3_20197122.repository;

import com.app.tarea3_20197122.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    Location findByLocationId(Integer locationId);
}
