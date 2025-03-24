package com.Smart_Student_Attendance_Backend.controller.mobile;
import com.Smart_Student_Attendance_Backend.dto.mobile.*;
import com.Smart_Student_Attendance_Backend.entity.mobile.Summery;
import com.Smart_Student_Attendance_Backend.service.mobile.StudentService;
import com.Smart_Student_Attendance_Backend.utill.StandardResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/student")
@CrossOrigin
public class StudentRegController {
    @Autowired
    private StudentService studentService;

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
        System.out.println("Reg No "+ studentSignInDTO.getStudentRegNo());
        System.out.println("Student password "+ studentSignInDTO.getStudentPassword());
        boolean massage=studentService.signInService(studentSignInDTO);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(200,"Success",massage)
                ,HttpStatus.OK);

    }

    //Get All Register Student Data
    @GetMapping("/getAllStudent")
    public ResponseEntity<StandardResponce> getAllStudent(){
        List<StudentRegDTO> studentRegDTO = studentService.getAllStudent();
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(200,"OK",studentRegDTO)
                ,HttpStatus.OK);
    }
//    ==========================================================================================================
    @PutMapping("/updateRegStudent")
    public ResponseEntity<StandardResponce> updateStudent(@RequestBody StudentRegDTO studentRegDTO){
        String massage = studentService.updateStudent(studentRegDTO);
//      Registation number only add both column in table
        String massageHistory=studentService.saveStudentHistory(studentRegDTO);
        String massageSummery=studentService.saveStudentSummery(studentRegDTO);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(201,"Updated",massageHistory+massageSummery)
                ,HttpStatus.CREATED);
    }
//    ================================================================================================================
    @DeleteMapping(path = "/deleteRegStudent/{studentRegNo}")
    public ResponseEntity<StandardResponce> deleteRegStudent(@PathVariable (value = "studentRegNo") String studentRegNo){
        String massage = studentService.deleteStudent(studentRegNo);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(200,"Deleted",massage)
                ,HttpStatus.OK);
    }

    //Student Attendance Mark
    @PostMapping("/attendMark")
    public ResponseEntity<StandardResponce> AttendMark(@RequestBody StudentCurrentAttendDTO studentAttendDTO){
        String massage=studentService.attendMarkStudent(studentAttendDTO);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(201,"Save",massage)
                ,HttpStatus.CREATED);
    }
//    ===========================================================================================
    @PostMapping("/acceptedAttendance")
    @Transactional(rollbackFor = Exception.class) // Ensures that the transaction will roll back if an exception occurs
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
//    ===========================================================================================

    @GetMapping("/getAllAcceptAttendance")
    public ResponseEntity<StandardResponce> getAllAcceptStudentAttend(){
        List<TotalAttendDTO> totalAttendDTO = studentService.getAllAcceptStudentAttend();
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(200,"Save",totalAttendDTO)
                ,HttpStatus.OK);
    }


    @GetMapping("/getAllAttendance")
    public ResponseEntity<StandardResponce> getAllStudentAttend(){
        List<StudentCurrentAttendDTO> studentAttendDTO = studentService.getAllStudentAttend();
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(200,"Save",studentAttendDTO)
                ,HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteAttendance/{studentRegNo}/{date}")
    public ResponseEntity<StandardResponce> deleteAttendance(@PathVariable (value = "studentRegNo") String studentRegNo,
                                   @PathVariable (value = "date") String date){
        System.out.println(studentRegNo+date);
        String massage = studentService.deleteAttendance(studentRegNo,date);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(200,"Deleted",massage)
                ,HttpStatus.OK);
    }

    @PostMapping("/addColumn")
    public ResponseEntity<StandardResponce> addColumn(@RequestParam String columnName) {
        // Pass the raw column name with slashes to the service layer
        String massage=studentService.addColumnToSummery(columnName);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(201,"Created",massage)
                ,HttpStatus.CREATED);
    }

    @PostMapping("/deleteColumn")
    public ResponseEntity<StandardResponce> deleteColumn(@RequestParam String columnName) {
        // Pass the raw column name with slashes to the service layer
        String massage = studentService.deleteColumnFromSummery(columnName);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(200,"Deleted",massage)
                ,HttpStatus.OK);
    }

    @GetMapping("/getAllSummeryData")
    public ResponseEntity<StandardResponce> getAllSummeryData() {
        List<Map<String, Object>> massage = studentService.getAllSummeryData(); // Fetch data from the service
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(200,"OK",massage)
                ,HttpStatus.OK);
    }
//    mobile app get data
    @GetMapping("/getAttendanceByRegNo/{regNo}")
    public ResponseEntity<StandardResponce> getAttendanceByRegNo(@PathVariable String regNo) {
        List<Map<String, Object>> massage=studentService.getAttendSummeryData(regNo);
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(201,"OK",massage)
                ,HttpStatus.OK);

    }

    @GetMapping("/dayByDayCounts")
    public ResponseEntity<StandardResponce> getAttendanceCountsDayByDay() {
        List<Map<String, Object>> massage = studentService.getAttendanceCountsDayByDay();
        return new ResponseEntity<StandardResponce>(
                new StandardResponce(200,"OK",massage)
                ,HttpStatus.OK);
    }

    @PostMapping("/createNotification")
    public  String createNotification(@RequestBody NotificationDTO notificationDTO){
       return studentService.createNotification(notificationDTO);
    }

    @GetMapping("/getAllNotifications")
    public List<NotificationDTO> getAllNotifications(){
        return studentService.getAllNotification();
    }
    @PutMapping("/updateNotification")
    public  String updateNotification(@RequestBody NotificationDTO notificationDTO){
        return studentService.updateNotification(notificationDTO);
    }

   @DeleteMapping("/deleteNotification/{id}")
    public String deleteNotification(@PathVariable int id){
        return studentService.deleteNotification(id);
    }

    @PostMapping("/savelecturehall")
    public String savelecturehall(@RequestBody LectureHallsDTO lectureHallsDTO){
        return studentService.savelecturehall(lectureHallsDTO);
    }

    @PutMapping("/updatelecturehall")
    public String updatelecturehall(@RequestBody LectureHallsDTO lectureHallsDTO){
        return studentService.updatelecturehall(lectureHallsDTO);
    }
    @GetMapping("/getAlllecturehall")
    public List<LectureHallsDTO> getAlllecturehall(){
        return studentService.getAllLecturehall();
    }
    @DeleteMapping("/deletelecturehall/{id}")
    public String deleteLecturehall(@PathVariable int id){

        return studentService.deleteLecturehall(id);
    }




}
