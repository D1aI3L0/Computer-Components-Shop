package com.example.springBackend_Hibernate.service;


import com.example.springBackend_Hibernate.MEntityNotFoundException;
import com.example.springBackend_Hibernate.entity.Processor;
import com.example.springBackend_Hibernate.repository.ProcessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessorService {

    @Autowired
    private ProcessorRepository processorRepository;

    public List<Processor> getAllProcessors() {
        return processorRepository.findAll();
    }

    public Optional<Processor> getProcessorById(Long id) {
        return processorRepository.findById(id);
    }

    public Processor createProcessor(Processor processor) {
        return processorRepository.save(processor);
    }

    public Processor updateProcessor(Long id, Processor processorDetails) throws MEntityNotFoundException {
        Processor processor = processorRepository.findById(id).orElseThrow(() -> new MEntityNotFoundException("Processor not found with id: " + id));
        processor.setThreadsCount(processorDetails.getThreadsCount());
        processor.setClockFrequency(processorDetails.getClockFrequency());
        processor.setMaxFrequency(processorDetails.getMaxFrequency());
        processor.setCpuCount(processorDetails.getCpuCount());
        return processorRepository.save(processor);
    }

    public void deleteProcessor(Long id) {
        processorRepository.deleteById(id);
    }
}
