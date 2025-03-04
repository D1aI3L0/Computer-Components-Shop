package com.example.springBackend_Hibernate.service;

import com.example.springBackend_Hibernate.MEntityNotFoundException;
import com.example.springBackend_Hibernate.entity.Client;
import com.example.springBackend_Hibernate.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client clientDetails) throws MEntityNotFoundException {
        Client client = clientRepository.findById(id).orElseThrow(() -> new MEntityNotFoundException("Client not found with id: " + id));
        client.setPhoneNumber(clientDetails.getPhoneNumber());
        client.setName(clientDetails.getName());
        client.setSurname(clientDetails.getSurname());
        client.setPatronymic(clientDetails.getPatronymic());
        client.setEmailAddress(clientDetails.getEmailAddress());
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}