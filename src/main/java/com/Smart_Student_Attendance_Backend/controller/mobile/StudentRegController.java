package com.Smart_Student_Attendance_Backend.controller.mobile;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentRegDTO;
import com.Smart_Student_Attendance_Backend.service.mobile.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student")
@CrossOrigin
public class StudentRegController {
    @Autowired
    private StudentService studentService;


    @PostMapping("/save")
    public String SaveStudent(@RequestBody StudentRegDTO studentRegDTO){
        System.out.println("Reg No "+ studentRegDTO.getStudentRegNo());
        System.out.println("Student password "+ studentRegDTO.getStudentPassword());
        System.out.println("Active Status "+ studentRegDTO.isActivestatus());
        String massage=studentService.saveStudent(studentRegDTO);

        return massage;

    }
}
