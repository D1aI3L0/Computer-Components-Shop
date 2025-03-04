package com.example.springBackend_Hibernate.service;


import com.example.springBackend_Hibernate.MEntityNotFoundException;
import com.example.springBackend_Hibernate.entity.Motherboard;
import com.example.springBackend_Hibernate.repository.MotherboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotherboardService {

    @Autowired
    private MotherboardRepository motherboardRepository;

    public List<Motherboard> getAllMotherboards() {
        return motherboardRepository.findAll();
    }

    public Optional<Motherboard> getMotherboardById(Long id) {
        return motherboardRepository.findById(id);
    }

    public Motherboard createMotherboard(Motherboard motherboard) {
        return motherboardRepository.save(motherboard);
    }

    public Motherboard updateMotherboard(Long id, Motherboard motherboardDetails) throws MEntityNotFoundException {
        Motherboard motherboard = motherboardRepository.findById(id).orElseThrow(() -> new MEntityNotFoundException("Motherboard not found with id: " + id));
        motherboard.setMemorySlots(motherboardDetails.getMemorySlots());
        motherboard.setChipset(motherboardDetails.getChipset());
        motherboard.setFormFactor(motherboardDetails.getFormFactor());
        return motherboardRepository.save(motherboard);
    }

    public void deleteMotherboard(Long id) {
        motherboardRepository.deleteById(id);
    }
}
