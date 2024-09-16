package com.Smart_Student_Attendance_Backend.service.impl;

import com.Smart_Student_Attendance_Backend.dto.StudentDTO;
import com.Smart_Student_Attendance_Backend.entity.Student;
import com.Smart_Student_Attendance_Backend.repo.StudentRepo;
import com.Smart_Student_Attendance_Backend.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceIMPL implements StudentService {
    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    private StudentRepo studentRepo;


    @Override
    public String saveStudent(StudentDTO studentDTO) {
        Student student= modelMapper.map(studentDTO,Student.class);
        if(!studentRepo.existsById(student.getStudentId())){
            studentRepo.save(student);
            return studentDTO.getStudentRegNo()+" Saved";
        }else {
            throw new DuplicateKeyException("Alredy Added");
        }



    }
}
