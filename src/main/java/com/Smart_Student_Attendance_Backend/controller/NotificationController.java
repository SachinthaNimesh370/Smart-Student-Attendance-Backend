package com.Smart_Student_Attendance_Backend.controller;

import com.Smart_Student_Attendance_Backend.dto.NotificationDTO;
import com.Smart_Student_Attendance_Backend.dto.ServiceResponceDTO;
import com.Smart_Student_Attendance_Backend.service.NotificationService;
import com.Smart_Student_Attendance_Backend.utill.StandardResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
@CrossOrigin
public class NotificationController {
    private  final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @PostMapping("/createNotification")
    public ResponseEntity<StandardResponce> createNotification(@RequestBody NotificationDTO notificationDTO){
        ServiceResponceDTO massage =notificationService.createNotification(notificationDTO);
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
    @GetMapping("/getAllNotifications")
    public ResponseEntity<StandardResponce> getAllNotifications(){
        ServiceResponceDTO massage= notificationService.getAllNotification();
        if(massage.isSuccess()){
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(200,"Success",massage.getObject())
                    ,HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(400,"Error",massage.getObject())
                    ,HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/updateNotification")
    public  ResponseEntity<StandardResponce> updateNotification(@RequestBody NotificationDTO notificationDTO){
        ServiceResponceDTO massage = notificationService.updateNotification(notificationDTO);
        if(massage.isSuccess()){
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(200,"Success",massage.getObject())
                    ,HttpStatus.OK);
        }else {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(400,"Error",massage.getObject())
                    ,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteNotification/{id}")
    public ResponseEntity<StandardResponce> deleteNotification(@PathVariable int id){
        ServiceResponceDTO massage =notificationService.deleteNotification(id);
        if(massage.isSuccess()){
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(200,"Success",massage.getObject())
                    ,HttpStatus.OK) ;
        }else {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(400,"Error",massage.getObject())
                    ,HttpStatus.BAD_REQUEST) ;
        }
    }
}
