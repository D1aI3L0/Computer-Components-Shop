package com.example.springBackend_Hibernate.controller;


import com.example.springBackend_Hibernate.entity.GraphicCard;
import com.example.springBackend_Hibernate.service.GraphicCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/graphic-cards")
public class GraphicCardController {

    @Autowired
    private GraphicCardService graphicCardService;

    @GetMapping
    public List<GraphicCard> getAllGraphicCards() {
        return graphicCardService.getAllGraphicCards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GraphicCard> getGraphicCardById(@PathVariable Long id) {
        return graphicCardService.getGraphicCardById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public GraphicCard createGraphicCard(@RequestBody GraphicCard graphicCard) {
        return graphicCardService.createGraphicCard(graphicCard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GraphicCard> updateGraphicCard(@PathVariable Long id, @RequestBody GraphicCard graphicCardDetails) {
        return ResponseEntity.ok(graphicCardService.updateGraphicCard(id, graphicCardDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGraphicCard(@PathVariable Long id) {
        graphicCardService.deleteGraphicCard(id);
        return ResponseEntity.noContent().build();
    }
}
