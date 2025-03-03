package com.example.springBackend_Hibernate.service;

import com.example.springBackend_Hibernate.entity.GraphicCard;
import com.example.springBackend_Hibernate.repository.GraphicCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GraphicCardService {

    @Autowired
    private GraphicCardRepository graphicCardRepository;

    public List<GraphicCard> getAllGraphicCards() {
        return graphicCardRepository.findAll();
    }

    public Optional<GraphicCard> getGraphicCardById(Long id) {
        return graphicCardRepository.findById(id);
    }

    public GraphicCard createGraphicCard(GraphicCard graphicCard) {
        return graphicCardRepository.save(graphicCard);
    }

    public GraphicCard updateGraphicCard(Long id, GraphicCard graphicCardDetails) {
        GraphicCard graphicCard = graphicCardRepository.findById(id).orElseThrow(() -> new RuntimeException("GraphicCard not found"));
        graphicCard.setGpuCount(graphicCardDetails.getGpuCount());
        graphicCard.setGpuFrequency(graphicCardDetails.getGpuFrequency());
        graphicCard.setMemoryCount(graphicCardDetails.getMemoryCount());
        graphicCard.setMemoryFrequency(graphicCardDetails.getMemoryFrequency());
        return graphicCardRepository.save(graphicCard);
    }

    public void deleteGraphicCard(Long id) {
        graphicCardRepository.deleteById(id);
    }
}
