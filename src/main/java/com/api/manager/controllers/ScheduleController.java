package com.api.manager.controllers;

import com.api.manager.dtos.ScheduleDto;
import com.api.manager.models.ScheduleModel;
import com.api.manager.services.ScheduleService;
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
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Object> saveSchedule(@RequestBody @Valid ScheduleDto scheduleDto ){
        Map<String, String> response = new HashMap<>();
        var scheduleModel = new ScheduleModel();
        BeanUtils.copyProperties(scheduleDto,scheduleModel);
        try{
            scheduleService.save(scheduleModel);
            response.put("message", "Schedule created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            response.put("ERROR", "TRUE");
            response.put("message", "Could not created schedule");
            response.put("err",e.getCause().getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<ScheduleModel>> getAllSchedules(){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByIdSchedule(@PathVariable(value = "id")Long id){
        Map<String, String> response = new HashMap<>();
        Optional<ScheduleModel> scheduleModelOptional = scheduleService.findById(id);
        if(!scheduleModelOptional.isPresent()){
            response.put("ERROR","True");
            response.put("message","Schedule not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSchedule(@PathVariable(value = "id")Long id){
        Map<String, String> response = new HashMap<>();

        Optional<ScheduleModel> scheduleModelOptional = scheduleService.findById(id);
        if(!scheduleModelOptional.isPresent()){
            response.put("ERROR","True");
            response.put("message","Schedule not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try{
            scheduleService.delete(scheduleModelOptional.get());
            response.put("message","Schedule deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            response.put("ERROR","True");
            response.put("message","Could not delete schedule");
            response.put("err",e.getCause().getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSchedule(@PathVariable(value = "id")Long id, @RequestBody ScheduleDto scheduleDto ) {
        Map<String, String> response = new HashMap<>();

        Optional<ScheduleModel> scheduleModelOptional = scheduleService.findById(id);
        if (!scheduleModelOptional.isPresent()) {
            response.put("ERROR", "True");
            response.put("message", "Schedule not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        var scheduleModel = scheduleModelOptional.get();
        BeanUtils.copyProperties(scheduleDto, scheduleModel);

        try {
            scheduleService.save(scheduleModel);
            response.put("message", "Schedule update successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("ERROR", "TRUE");
            response.put("message", "Could not update schedule");
            response.put("err",e.getCause().getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
