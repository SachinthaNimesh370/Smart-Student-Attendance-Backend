package com.Smart_Student_Attendance_Backend.service.mobile.impl;

import com.Smart_Student_Attendance_Backend.dto.mobile.LectureHallsDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.ServiceResponceDTO;
import com.Smart_Student_Attendance_Backend.entity.mobile.LectureHalls;
import com.Smart_Student_Attendance_Backend.repo.mobile.LecturehallRepo;
import com.Smart_Student_Attendance_Backend.service.mobile.LectureHallService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LectureHallServiceIMPL implements LectureHallService {
    private final LecturehallRepo lecturehallRepo;
    private final ModelMapper modelMapper;

    public LectureHallServiceIMPL(LecturehallRepo lecturehallRepo, ModelMapper modelMapper) {
        this.lecturehallRepo = lecturehallRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ServiceResponceDTO savelecturehall(LectureHallsDTO lectureHallsDTO) {
        try {
            LectureHalls lectureHalls= modelMapper.map(lectureHallsDTO, LectureHalls.class);
            lecturehallRepo.save(lectureHalls);
            return new ServiceResponceDTO(true,"Saved Lecture Hall");
        }catch (Exception e){
            return new ServiceResponceDTO(false,"Please Try Again"+e.getMessage());
        }
    }

    @Override
    public ServiceResponceDTO updatelecturehall(LectureHallsDTO lectureHallsDTO) {
        try {
            LectureHalls lectureHalls= modelMapper.map(lectureHallsDTO, LectureHalls.class);
            lecturehallRepo.save(lectureHalls);
            return new ServiceResponceDTO(true,"Updated Lecture Hall");
        }catch (Exception e){
            return new ServiceResponceDTO(false,"Please Try Again"+e.getMessage());
        }
    }

    @Override
    public ServiceResponceDTO getAllLecturehall() {
        List<LectureHalls> alllectureHalls = lecturehallRepo.findAll();
        if (!alllectureHalls.isEmpty()) {
            List<LectureHallsDTO> getAlllectureHalls = modelMapper.map(alllectureHalls, new TypeToken<List<LectureHallsDTO>>(){}.getType());
            return new ServiceResponceDTO(true,getAlllectureHalls);
        } else {
            return new ServiceResponceDTO(false,"Please Try Again");
        }
    }

    @Override
    public ServiceResponceDTO deleteLecturehall(int id) {
        try {
            lecturehallRepo.deleteById(id);
            return new ServiceResponceDTO(true,"Success Full Notification Delete");
        }catch (Exception e){
            return new ServiceResponceDTO(false,"Please Try Again"+e.getMessage());
        }
    }

}
