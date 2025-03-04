package com.example.springBackend_Hibernate.controller;


import com.example.springBackend_Hibernate.MEntityNotFoundException;
import com.example.springBackend_Hibernate.entity.Motherboard;
import com.example.springBackend_Hibernate.service.MotherboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motherboards")
public class MotherboardController {

    @Autowired
    private MotherboardService motherboardService;

    @GetMapping
    public List<Motherboard> getAllMotherboards() {
        return motherboardService.getAllMotherboards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motherboard> getMotherboardById(@PathVariable Long id) {
        return motherboardService.getMotherboardById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Motherboard createMotherboard(@RequestBody Motherboard motherboard) {
        return motherboardService.createMotherboard(motherboard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Motherboard> updateMotherboard(@PathVariable Long id, @RequestBody Motherboard motherboardDetails) throws MEntityNotFoundException {
        return ResponseEntity.ok(motherboardService.updateMotherboard(id, motherboardDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotherboard(@PathVariable Long id) {
        motherboardService.deleteMotherboard(id);
        return ResponseEntity.noContent().build();
    }
}
