package com.api.manager.controllers;

import com.api.manager.dtos.ServiceDto;
import com.api.manager.models.ServiceModel;
import com.api.manager.services.ServiceService;
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
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @PostMapping
    public ResponseEntity<Object> saveService(@RequestBody @Valid ServiceDto serviceDto ){
        Map<String, String> response = new HashMap<>();
        var serviceModel = new ServiceModel();
        BeanUtils.copyProperties(serviceDto,serviceModel);
        try{
            serviceService.save(serviceModel);
            response.put("message", "Service created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            response.put("ERROR", "TRUE");
            response.put("message", "Could not created service");
            response.put("err",e.getCause().getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<ServiceModel>> getAllServices(){
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByIdService(@PathVariable(value = "id")Long id){
        Map<String, String> response = new HashMap<>();
        Optional<ServiceModel> serviceModelOptional = serviceService.findById(id);
        if(!serviceModelOptional.isPresent()){
            response.put("ERROR","True");
            response.put("message","Service not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(serviceModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable(value = "id")Long id){
        Map<String, String> response = new HashMap<>();

        Optional<ServiceModel> serviceModelOptional = serviceService.findById(id);
        if(!serviceModelOptional.isPresent()){
            response.put("ERROR","True");
            response.put("message","Service not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try{
            serviceService.delete(serviceModelOptional.get());
            response.put("message","Service deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            response.put("ERROR","True");
            response.put("message","Could not delete service");
            response.put("err",e.getCause().getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateService(@PathVariable(value = "id")Long id, @RequestBody ServiceDto serviceDto ) {
        Map<String, String> response = new HashMap<>();

        Optional<ServiceModel> serviceModelOptional = serviceService.findById(id);
        if (!serviceModelOptional.isPresent()) {
            response.put("ERROR", "True");
            response.put("message", "Service not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        var serviceModel = serviceModelOptional.get();
        BeanUtils.copyProperties(serviceDto, serviceModel);

        try {
            serviceService.save(serviceModel);
            response.put("message", "Service update successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("ERROR", "TRUE");
            response.put("message", "Could not update service");
            response.put("err",e.getCause().getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
