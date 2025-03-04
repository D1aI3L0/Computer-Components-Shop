package com.example.springBackend_Hibernate.controller;


import com.example.springBackend_Hibernate.MEntityNotFoundException;
import com.example.springBackend_Hibernate.entity.Processor;
import com.example.springBackend_Hibernate.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/processors")
public class ProcessorController {

    @Autowired
    private ProcessorService processorService;

    @GetMapping
    public List<Processor> getAllProcessors() {
        return processorService.getAllProcessors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Processor> getProcessorById(@PathVariable Long id) {
        return processorService.getProcessorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Processor createProcessor(@RequestBody Processor processor) {
        return processorService.createProcessor(processor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Processor> updateProcessor(@PathVariable Long id, @RequestBody Processor processorDetails) throws MEntityNotFoundException {
        return ResponseEntity.ok(processorService.updateProcessor(id, processorDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcessor(@PathVariable Long id) {
        processorService.deleteProcessor(id);
        return ResponseEntity.noContent().build();
    }
}
