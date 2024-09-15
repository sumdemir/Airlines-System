package com.example.airlinessystem.repository;

import com.example.airlinessystem.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepo extends JpaRepository <Route, Long> {

}
