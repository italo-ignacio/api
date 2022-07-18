package com.api.manager.controllers;

import com.api.manager.dtos.AnimalDto;
import com.api.manager.models.AnimalModel;
import com.api.manager.services.AnimalService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @PostMapping
    public ResponseEntity<Object> saveAnimal(@RequestBody @Valid AnimalDto animalDto ){
        Map<String, String> response = new HashMap<>();

        var animalModel = new AnimalModel();
        BeanUtils.copyProperties(animalDto,animalModel);
        try{
            animalService.save(animalModel);
            response.put("message", "Animal created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            response.put("ERROR", "TRUE");
            response.put("message", "Could not created animal");
            response.put("err", e.getCause().getCause().getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<AnimalModel>> getAllAnimals(){
        return ResponseEntity.status(HttpStatus.OK).body(animalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByIdAnimal(@PathVariable(value = "id")Long id){
        Map<String, String> response = new HashMap<>();
        Optional<AnimalModel> animalModelOptional = animalService.findById(id);
        if(!animalModelOptional.isPresent()){
            response.put("ERROR","True");
            response.put("message","Animal not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(animalModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAnimal(@PathVariable(value = "id")Long id){
        Map<String, String> response = new HashMap<>();

        Optional<AnimalModel> animalModelOptional = animalService.findById(id);
        if(!animalModelOptional.isPresent()){
            response.put("ERROR","True");
            response.put("message","Animal not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try{
            animalService.delete(animalModelOptional.get());
            response.put("message","Animal deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            response.put("ERROR","True");
            response.put("message","Could not delete animal");
            response.put("err",e.getCause().getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAnimal(@PathVariable(value = "id")Long id, @RequestBody AnimalDto animalDto ) {
        Map<String, String> response = new HashMap<>();

        Optional<AnimalModel> animalModelOptional = animalService.findById(id);
        if (!animalModelOptional.isPresent()) {
            response.put("ERROR", "True");
            response.put("message", "Animal not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        var animalModel = animalModelOptional.get();
        BeanUtils.copyProperties(animalDto, animalModel);

        try {
            animalService.save(animalModel);
            response.put("message", "Animal update successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            String error = e.getCause().getCause().getMessage();
            response.put("ERROR", "TRUE");
            response.put("message", "Could not update animal");
            response.put("err", error);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
