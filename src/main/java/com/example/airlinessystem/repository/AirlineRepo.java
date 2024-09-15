package com.example.airlinessystem.repository;


import com.example.airlinessystem.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineRepo extends JpaRepository<Airline, Long> {
    Airline findByName(String name);

    @Query("SELECT a FROM AirlineEntity  a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Airline> findByNameContainingIgnoreCase(@Param("keyword") String keyword);
}
