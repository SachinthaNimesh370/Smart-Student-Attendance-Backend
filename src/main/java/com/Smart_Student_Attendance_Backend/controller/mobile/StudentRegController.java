package com.Smart_Student_Attendance_Backend.controller.mobile;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentAttendDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentRegDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentSignInDTO;
import com.Smart_Student_Attendance_Backend.service.mobile.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@CrossOrigin
public class StudentRegController {
    @Autowired
    private StudentService studentService;

    //Student Registation
    @PostMapping("/signUp")
    public String SaveStudent(@RequestBody StudentRegDTO studentRegDTO){
        System.out.println("Reg No "+ studentRegDTO.getStudentRegNo());
        System.out.println("Email "+ studentRegDTO.getStudentEmail());
        System.out.println("Student password "+ studentRegDTO.getStudentPassword());
        System.out.println("Active Status "+ studentRegDTO.isActivestatus());
        String massage=studentService.saveStudent(studentRegDTO);
        return massage;
    }

    //Student Sign IN
    @PostMapping("/signIn")
    public boolean SignIn(@RequestBody StudentSignInDTO studentSignInDTO){
        System.out.println("Reg No "+ studentSignInDTO.getStudentRegNo());
        System.out.println("Student password "+ studentSignInDTO.getStudentPassword());
        boolean massage=studentService.signInService(studentSignInDTO);

        return massage;

    }
    //Student Attendance Mark
    @PostMapping("/attendMark")
    public String AttendMark(@RequestBody StudentAttendDTO studentAttendDTO){
        System.out.println("Reg No "+ studentAttendDTO.getStudentRegNo());
        System.out.println("Time "+ studentAttendDTO.getTime());
        System.out.println("Date "+ studentAttendDTO.getDate());
        System.out.println("Location "+ studentAttendDTO.getLocation());
        System.out.println("Attendance "+ studentAttendDTO.isAttendance());

        String massage=studentService.attendMarkStudent(studentAttendDTO);
        System.out.println(studentAttendDTO);
        return massage;
    }

    //Get All Register Student Data
    @GetMapping("/getAllStudent")
    public List<StudentRegDTO> getAllStudent(){
        List<StudentRegDTO> studentRegDTO = studentService.getAllStuden();
        return studentRegDTO;
    }

}
