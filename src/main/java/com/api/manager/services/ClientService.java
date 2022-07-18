package com.api.manager.services;

import com.api.manager.models.ClientModel;
import com.api.manager.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Transactional
    public ClientModel save(ClientModel clientModel) {return clientRepository.save(clientModel);}

    public List<ClientModel> findAll() {return clientRepository.findAll();}

    public Optional<ClientModel> findById(Long id) {return clientRepository.findById(id);}

    public Optional<ClientModel> findByEmail(String email) {return clientRepository.findByEmail(email);}

    @Transactional
    public void delete(ClientModel clientModel) {
        clientRepository.delete(clientModel);
    }
}
