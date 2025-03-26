package com.Smart_Student_Attendance_Backend.controller.mobileApp;

import com.Smart_Student_Attendance_Backend.dto.mobile.ServiceResponceDTO;
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
        ServiceResponceDTO massage=studentService.saveStudent(studentRegDTO);
        if(massage.isSuccess()){
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(201,"Success",massage.getObject())
                    , HttpStatus.CREATED);
        }else {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(400,"Error",massage.getObject())
                    , HttpStatus.BAD_REQUEST);
        }

    }

    //Student Sign IN
    @PostMapping("/signIn")
    public ResponseEntity<StandardResponce> SignIn(@RequestBody StudentSignInDTO studentSignInDTO){
        ServiceResponceDTO massage=studentService.signInService(studentSignInDTO);
        if(massage.isSuccess()){
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(200,"Success",massage.getObject())
                    ,HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(404,"Error",massage.getObject())
                    ,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/attendMark")
    public ResponseEntity<StandardResponce> AttendMark(@RequestBody StudentCurrentAttendDTO studentAttendDTO){
        ServiceResponceDTO massage=studentService.attendMarkStudent(studentAttendDTO);
        if(massage.isSuccess()){
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(201,"Success",massage.getObject())
                    ,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(400,"Error",massage.getObject())
                    ,HttpStatus.BAD_REQUEST);
        }

    }
}
