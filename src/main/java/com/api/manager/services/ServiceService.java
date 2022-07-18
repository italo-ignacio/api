package com.api.manager.services;

import com.api.manager.models.ServiceModel;
import com.api.manager.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    @Transactional
    public ServiceModel save(ServiceModel serviceModel) {return serviceRepository.save(serviceModel);}

    public List<ServiceModel> findAll() {return serviceRepository.findAll();}

    public Optional<ServiceModel> findById(Long id) {return serviceRepository.findById(id);}

    @Transactional
    public void delete(ServiceModel serviceModel) {
        serviceRepository.delete(serviceModel);
    }
}
