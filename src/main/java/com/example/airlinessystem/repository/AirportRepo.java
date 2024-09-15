package com.example.airlinessystem.repository;

import com.example.airlinessystem.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepo extends JpaRepository<Airport, Long> {

    Airport findByName(String name);

    @Query("SELECT a FROM Airport a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Airport> findByNameContainingIgnoreCase(@Param("keyword") String keyword);

}
