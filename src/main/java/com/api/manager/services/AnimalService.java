package com.api.manager.services;

import com.api.manager.models.AnimalModel;
import com.api.manager.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class AnimalService {

    @Autowired
    AnimalRepository animalRepository;

    @Transactional
    public AnimalModel save(AnimalModel animalModel) {
        return animalRepository.save(animalModel);
    }

    public Iterable<AnimalModel> findAll() {
        return animalRepository.findAll();
    }

    public Optional<AnimalModel> findById(Long id) {return animalRepository.findById(id);}

    @Transactional
    public void delete(AnimalModel animalModel) {
        animalRepository.delete(animalModel);
    }
}
