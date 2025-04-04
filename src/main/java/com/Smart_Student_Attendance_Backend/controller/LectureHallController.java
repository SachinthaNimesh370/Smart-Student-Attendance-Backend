package com.Smart_Student_Attendance_Backend.controller;

import com.Smart_Student_Attendance_Backend.dto.LectureHallsDTO;
import com.Smart_Student_Attendance_Backend.dto.ServiceResponceDTO;
import com.Smart_Student_Attendance_Backend.service.LectureHallService;
import com.Smart_Student_Attendance_Backend.utill.StandardResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lecturehall")
@CrossOrigin
public class LectureHallController {

    private  final LectureHallService lectureHallService;

    public LectureHallController(LectureHallService lectureHallService) {
        this.lectureHallService = lectureHallService;
    }

    @PostMapping("/savelecturehall")
    public ResponseEntity<StandardResponce> savelecturehall(@RequestBody LectureHallsDTO lectureHallsDTO){
        ServiceResponceDTO massage = lectureHallService.savelecturehall(lectureHallsDTO);
        if(massage.isSuccess()){
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(200,"Success",massage.getObject())
                    , HttpStatus.OK);
        }else {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(400,"Error",massage.getObject())
                    ,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updatelecturehall")
    public ResponseEntity<StandardResponce> updatelecturehall(@RequestBody LectureHallsDTO lectureHallsDTO){
        ServiceResponceDTO massage=lectureHallService.updatelecturehall(lectureHallsDTO);
        if(massage.isSuccess()){
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(200,"Success",massage.getObject()),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(400,"Error",massage.getObject()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAlllecturehall")
    public ResponseEntity<StandardResponce> getAlllecturehall(){
        ServiceResponceDTO massage = lectureHallService.getAllLecturehall();
        if(massage.isSuccess()){
            System.out.println(massage.getObject());
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(200,"Success",massage.getObject())
                    ,HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(400,"Error",massage.getObject()),
                    HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deletelecturehall/{id}")
    public ResponseEntity<StandardResponce> deleteLecturehall(@PathVariable int id){
        ServiceResponceDTO massage =lectureHallService.deleteLecturehall(id);
        if(massage.isSuccess()){
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(200,"Success",massage.getObject())
                    ,HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(400,"Error",massage.getObject()),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
