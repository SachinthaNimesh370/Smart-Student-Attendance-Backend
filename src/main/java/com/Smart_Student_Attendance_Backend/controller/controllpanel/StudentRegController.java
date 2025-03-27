package com.Smart_Student_Attendance_Backend.controller.controllpanel;
import com.Smart_Student_Attendance_Backend.dto.mobile.*;
import com.Smart_Student_Attendance_Backend.service.mobile.StudentService;
import com.Smart_Student_Attendance_Backend.utill.StandardResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/controller")
@CrossOrigin
public class StudentRegController {

    private final StudentService studentService;

    public StudentRegController(StudentService studentService) {
        this.studentService = studentService;
    }

    //Get All Register Student Data
    @GetMapping("/getAllStudent")
    public ResponseEntity<StandardResponce> getAllStudent(){
        ServiceResponceDTO massage = studentService.getAllStudent();
        if(massage.isSuccess()){
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(200,"Success",massage.getObject())
                    ,HttpStatus.OK);
        }else {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(500,"Error",massage.getObject())
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateRegStudent")
    public ResponseEntity<StandardResponce> updateStudent(@RequestBody StudentRegDTO studentRegDTO){
        ServiceResponceDTO message = studentService.updateStudentWithHistoryAndSummary(studentRegDTO);
        if(message.isSuccess()){
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(200,"Success",message.getObject())
                    ,HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(500,"Error",message.getObject())
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/deleteRegStudent/{studentRegNo}")
    public ResponseEntity<StandardResponce> deleteRegStudent(@PathVariable (value = "studentRegNo") String studentRegNo){
        ServiceResponceDTO massage = studentService.deleteStudent(studentRegNo);
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

//==========================================================================================================
    @PostMapping("/acceptedAttendance")
    public ResponseEntity<StandardResponce> acceptedAttendance(@RequestBody StudentCurrentAttendDTO studentAttendDTO) {
        try {
            // Perform the first operation
            String message = studentService.acceptedAttendance(studentAttendDTO);

            // Perform the second operation
            String messageSummery = studentService.markAttendInSummery(studentAttendDTO);

            // If both operations succeed, return the response
            return new ResponseEntity<>(
                    new StandardResponce(201, "Accepted", message + messageSummery),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            // If any exception occurs, the transaction will roll back, canceling both operations
            return new ResponseEntity<>(
                    new StandardResponce(500, "Failed", "Attendance operation failed due to: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    @GetMapping("/getAllAcceptAttendance")
    public ResponseEntity<StandardResponce> getAllAcceptStudentAttend(){
        ServiceResponceDTO massage = studentService.getAllAcceptStudentAttend();
        if(massage.isSuccess()){
            return new ResponseEntity<StandardResponce>(
                new StandardResponce(200,"Success",massage.getObject())
                ,HttpStatus.OK);
        }else {
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(404,"Error",massage.getObject())
                    ,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAllAttendance")
    public ResponseEntity<StandardResponce> getAllStudentAttend(){
        ServiceResponceDTO massage = studentService.getAllStudentAttend();
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

    @DeleteMapping(path = "/deleteAttendance/{studentRegNo}/{date}")
    public ResponseEntity<StandardResponce> deleteAttendance(
            @PathVariable (value = "studentRegNo") String studentRegNo,
                                   @PathVariable (value = "date") String date){
        ServiceResponceDTO massage = studentService.deleteAttendance(studentRegNo,date);
        if(massage.isSuccess()){
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(200,"Success",massage)
                    ,HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(400,"Error",massage)
                    ,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addColumn")
    public ResponseEntity<StandardResponce> addColumn(@RequestParam String columnName) {
        ServiceResponceDTO massage=studentService.addColumnToSummery(columnName);
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

    @PostMapping("/deleteColumn")
    public ResponseEntity<StandardResponce> deleteColumn(@RequestParam String columnName) {
        ServiceResponceDTO massage = studentService.deleteColumnFromSummery(columnName);
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

    @GetMapping("/getAllSummeryData")
    public ResponseEntity<StandardResponce> getAllSummeryData() {
        ServiceResponceDTO massage = studentService.getAllSummeryData();
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
    @GetMapping("/getAttendanceByRegNo/{regNo}")
    public ResponseEntity<StandardResponce> getAttendanceByRegNo(@PathVariable String regNo) {
        ServiceResponceDTO massage=studentService.getAttendSummeryData(regNo);
        if(massage.isSuccess()){
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(201,"Success",massage.getObject())
                    ,HttpStatus.OK);
        }else{
            return new ResponseEntity<StandardResponce>(
                    new StandardResponce(400,"Error",massage.getObject())
                    ,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/dayByDayCounts")
    public ResponseEntity<StandardResponce> getAttendanceCountsDayByDay() {
        ServiceResponceDTO massage = studentService.getAttendanceCountsDayByDay();
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
    @PostMapping("/createNotification")
    public  ResponseEntity<StandardResponce> createNotification(@RequestBody NotificationDTO notificationDTO){
        ServiceResponceDTO massage =studentService.createNotification(notificationDTO);
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
    @GetMapping("/getAllNotifications")
    public ResponseEntity<StandardResponce> getAllNotifications(){
        ServiceResponceDTO massage= studentService.getAllNotification();
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
        ServiceResponceDTO massage = studentService.updateNotification(notificationDTO);
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
       ServiceResponceDTO massage =studentService.deleteNotification(id);
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

    @PostMapping("/savelecturehall")
    public ResponseEntity<StandardResponce> savelecturehall(@RequestBody LectureHallsDTO lectureHallsDTO){
        ServiceResponceDTO massage = studentService.savelecturehall(lectureHallsDTO);
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

    @PutMapping("/updatelecturehall")
    public ResponseEntity<StandardResponce> updatelecturehall(@RequestBody LectureHallsDTO lectureHallsDTO){
        ServiceResponceDTO massage=studentService.updatelecturehall(lectureHallsDTO);
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
        ServiceResponceDTO massage = studentService.getAllLecturehall();
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
        ServiceResponceDTO massage =studentService.deleteLecturehall(id);
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
