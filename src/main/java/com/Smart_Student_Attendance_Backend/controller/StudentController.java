package com.Smart_Student_Attendance_Backend.controller;
import com.Smart_Student_Attendance_Backend.dto.StudentDTO;
import com.Smart_Student_Attendance_Backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;


    @PostMapping("/save")
    public String SaveStudent(@RequestBody StudentDTO studentDTO){
        System.out.println("Reg No "+studentDTO.getStudentRegNo());
        System.out.println("Student password "+studentDTO.getStudentPassword());
        System.out.println("Active Status "+studentDTO.isActivestatus());
        String massage=studentService.saveStudent(studentDTO);

        return massage;

    }
}
