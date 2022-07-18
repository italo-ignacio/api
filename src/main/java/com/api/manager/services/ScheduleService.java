package com.api.manager.services;

import com.api.manager.models.ScheduleModel;
import com.api.manager.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleModel save(ScheduleModel scheduleModel) {return scheduleRepository.save(scheduleModel);}

    public List<ScheduleModel> findAll() {return scheduleRepository.findAll();}

    public Optional<ScheduleModel> findById(Long id) {return scheduleRepository.findById(id);}

    @Transactional
    public void delete(ScheduleModel scheduleModel) {
        scheduleRepository.delete(scheduleModel);
    }
}
