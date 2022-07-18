package com.api.manager.controllers;

import com.api.manager.dtos.ClientDto;
import com.api.manager.models.ClientModel;
import com.api.manager.services.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping
    public ResponseEntity<Object> saveClient(@RequestBody @Valid ClientDto clientDto){
        Map<String, String> response = new HashMap<>();
        var clientModel = new ClientModel();

        BeanUtils.copyProperties(clientDto,clientModel);

        try{
            clientService.save(clientModel);
            response.put("message", "Client created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            String error = e.getCause().getCause().getMessage();
            response.put("ERROR", "TRUE");
            if(error.contains("Duplicate entry")){
                response.put("message", "E-mail already exists");
            }else {
                response.put("message", "Could not created client");
                response.put("err",error);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<ClientModel>> getAllClients(){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByIdClient(@PathVariable(value = "id") Long id){
        Map<String, String> response = new HashMap<>();
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if(!clientModelOptional.isPresent()){
            response.put("ERROR", "TRUE");
            response.put("message", "Client not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientModelOptional.get());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id")Long id){
        Map<String, String> response = new HashMap<>();
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if(!clientModelOptional.isPresent()){
            response.put("ERROR", "TRUE");
            response.put("message", "Client not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try{
            clientService.delete(clientModelOptional.get());
            response.put("message", "Client successfully deleted");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            response.put("ERROR", "TRUE");
            response.put("message", "Could not delete client");
            response.put("err", e.getCause().getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id")Long id, @RequestBody @Valid ClientDto clientDto){
        Map<String, String> response = new HashMap<>();
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if(!clientModelOptional.isPresent()){
            response.put("ERROR", "TRUE");
            response.put("message", "Client not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        var clientModel = clientModelOptional.get();
        BeanUtils.copyProperties(clientDto,clientModel);
        try{
            clientService.save(clientModel);
            response.put("message", "Client updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            String error = e.getCause().getCause().getMessage();
            response.put("ERROR", "TRUE");
            if(error.contains("Duplicate entry")){
                response.put("message", "E-mail already exists");
            }else {
                response.put("message", "Could not update client");
                response.put("err", error);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
