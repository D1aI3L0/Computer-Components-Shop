package com.example.springBackend_Hibernate.controller;


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

    // Получить все материнские платы
    @GetMapping
    public List<Motherboard> getAllMotherboards() {
        return motherboardService.getAllMotherboards();
    }

    // Получить материнскую плату по ID
    @GetMapping("/{id}")
    public ResponseEntity<Motherboard> getMotherboardById(@PathVariable Long id) {
        return motherboardService.getMotherboardById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Создать новую материнскую плату
    @PostMapping
    public Motherboard createMotherboard(@RequestBody Motherboard motherboard) {
        return motherboardService.createMotherboard(motherboard);
    }

    // Обновить материнскую плату
    @PutMapping("/{id}")
    public ResponseEntity<Motherboard> updateMotherboard(@PathVariable Long id, @RequestBody Motherboard motherboardDetails) {
        return ResponseEntity.ok(motherboardService.updateMotherboard(id, motherboardDetails));
    }

    // Удалить материнскую плату
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotherboard(@PathVariable Long id) {
        motherboardService.deleteMotherboard(id);
        return ResponseEntity.noContent().build();
    }
}
