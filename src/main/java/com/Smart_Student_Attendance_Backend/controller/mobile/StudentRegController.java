package com.Smart_Student_Attendance_Backend.controller.mobile;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentRegDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentSignInDTO;
import com.Smart_Student_Attendance_Backend.service.mobile.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student")
@CrossOrigin
public class StudentRegController {
    @Autowired
    private StudentService studentService;


    @PostMapping("/signUp")
    public String SaveStudent(@RequestBody StudentRegDTO studentRegDTO){
//        studentRegDTO.setStudentEmail("asdf");
        System.out.println("Reg No "+ studentRegDTO.getStudentRegNo());
        System.out.println("Email "+ studentRegDTO.getStudentEmail());
        System.out.println("Student password "+ studentRegDTO.getStudentPassword());
        System.out.println("Active Status "+ studentRegDTO.isActivestatus());

        String massage=studentService.saveStudent(studentRegDTO);

        return massage;

    }

    @PostMapping("/signIn")
    public String SignIn(@RequestBody StudentSignInDTO studentSignInDTO){
        System.out.println("Reg No "+ studentSignInDTO.getStudentRegNo());
        System.out.println("Student password "+ studentSignInDTO.getStudentPassword());
        String massage=studentService.signInService(studentSignInDTO);

        return massage;

    }

}
