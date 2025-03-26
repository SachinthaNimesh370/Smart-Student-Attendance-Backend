package com.Smart_Student_Attendance_Backend.controller.mobileApp;

import com.Smart_Student_Attendance_Backend.dto.mobile.StudentCurrentAttendDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentRegDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentSignInDTO;
import com.Smart_Student_Attendance_Backend.service.mobile.StudentService;
import com.Smart_Student_Attendance_Backend.utill.StandardResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
public class StudentAppController {
    private  final StudentService studentService;

    public StudentAppController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<StandardResponce> SaveStudent(@RequestBody StudentRegDTO studentRegDTO){
        String massage=studentService.saveStudent(studentRegDTO);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(201,"Save",massage)
                , HttpStatus.CREATED);
    }

    //Student Sign IN
    @PostMapping("/signIn")
    public ResponseEntity<StandardResponce> SignIn(@RequestBody StudentSignInDTO studentSignInDTO){
        boolean massage=studentService.signInService(studentSignInDTO);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(200,"Success",massage)
                ,HttpStatus.OK);

    }

    @PostMapping("/attendMark")
    public ResponseEntity<StandardResponce> AttendMark(@RequestBody StudentCurrentAttendDTO studentAttendDTO){
        String massage=studentService.attendMarkStudent(studentAttendDTO);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(201,"Save",massage)
                ,HttpStatus.CREATED);
    }
}
