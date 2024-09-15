package com.example.airlinessystem.controller;

import com.example.airlinessystem.model.Route;
import com.example.airlinessystem.model.dto.route.request.RouteCreateRequest;
import com.example.airlinessystem.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping
    public ResponseEntity<Route> addRoute(
            @RequestBody final RouteCreateRequest request
    ){
        final Route newRoute = routeService.createRoute(request);

        return ResponseEntity.ok(newRoute);
    }

    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes(){
        List<Route> routes =routeService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id){
        routeService.deleteRouteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Route> getRouteById(@RequestParam Long id) {
        Optional<Route> route = Optional.ofNullable(routeService.getRouteById(id));


        if (route.isPresent()) {
            return ResponseEntity.ok(route.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
