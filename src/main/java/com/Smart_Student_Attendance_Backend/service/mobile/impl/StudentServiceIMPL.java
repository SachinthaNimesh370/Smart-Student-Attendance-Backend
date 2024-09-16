package com.Smart_Student_Attendance_Backend.service.mobile.impl;

import com.Smart_Student_Attendance_Backend.dto.mobile.StudentRegDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentSignInDTO;
import com.Smart_Student_Attendance_Backend.entity.mobile.StudentReg;
import com.Smart_Student_Attendance_Backend.repo.mobile.StudentRegRepo;
import com.Smart_Student_Attendance_Backend.service.mobile.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceIMPL implements StudentService {
    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    private StudentRegRepo studentRegRepo;



    @Override
    public String saveStudent(StudentRegDTO studentRegDTO) {
        StudentReg studentReg = modelMapper.map(studentRegDTO, StudentReg.class);
        if(!studentRegRepo.existsByStudentRegNoEquals(studentReg.getStudentRegNo())){
            studentRegRepo.save(studentReg);
            return studentRegDTO.getStudentRegNo()+" Saved";
        }else {
            return"Alredy Added";
        }
    }

    @Override
    public String signInService(StudentSignInDTO studentSignInDTO) {
        StudentReg studentReg = modelMapper.map(studentSignInDTO, StudentReg.class);
        if(studentRegRepo.existsByStudentRegNoEqualsAndStudentPasswordEqualsAndActivestatusEquals(studentReg.getStudentRegNo(),studentReg.getStudentPassword(),true)){
            return studentReg.getStudentRegNo()+" Sign In Successfuly";
        }else {
           return "Incorrect Registation Number OR Password";
        }

    }
}
