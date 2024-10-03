package com.Smart_Student_Attendance_Backend.controller.mobile;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentCurrentAttendDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentRegDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentSignInDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.TotalAttendDTO;
import com.Smart_Student_Attendance_Backend.entity.mobile.Summery;
import com.Smart_Student_Attendance_Backend.service.mobile.StudentService;
import com.Smart_Student_Attendance_Backend.utill.StandardResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/student")
@CrossOrigin
public class StudentRegController {
    @Autowired
    private StudentService studentService;

    //Student Registation
    @PostMapping("/signUp")
    public ResponseEntity<StandardResponce> SaveStudent(@RequestBody StudentRegDTO studentRegDTO){
        System.out.println("Reg No "+ studentRegDTO.getStudentRegNo());
        System.out.println("Email "+ studentRegDTO.getStudentEmail());
        System.out.println("Student password "+ studentRegDTO.getStudentPassword());
        System.out.println("Active Status "+ studentRegDTO.isActivestatus());
        String massage=studentService.saveStudent(studentRegDTO);
//        String massageHistory=studentService.saveStudentHistory(studentRegDTO.getStudentRegNo());
        ResponseEntity<StandardResponce> response=
                new ResponseEntity<StandardResponce>(
                        new StandardResponce(201,"Save",massage)
                        , HttpStatus.CREATED);
        return response;
    }

    //Student Sign IN
    @PostMapping("/signIn")
    public ResponseEntity<StandardResponce> SignIn(@RequestBody StudentSignInDTO studentSignInDTO){
        System.out.println("Reg No "+ studentSignInDTO.getStudentRegNo());
        System.out.println("Student password "+ studentSignInDTO.getStudentPassword());
        boolean massage=studentService.signInService(studentSignInDTO);

        ResponseEntity<StandardResponce> response=
                new ResponseEntity<StandardResponce>(
                        new StandardResponce(200,"Success",massage)
                        ,HttpStatus.OK);
        return response;

    }

    //Get All Register Student Data
    @GetMapping("/getAllStudent")
    public ResponseEntity<StandardResponce> getAllStudent(){
        List<StudentRegDTO> studentRegDTO = studentService.getAllStudent();
        ResponseEntity<StandardResponce> response=
                new ResponseEntity<StandardResponce>(
                        new StandardResponce(200,"OK",studentRegDTO)
                        ,HttpStatus.OK);
        return response;
    }
//    ==========================================================================================================
    @PutMapping("/updateRegStudent")
    public ResponseEntity<StandardResponce> updateStudent(@RequestBody StudentRegDTO studentRegDTO){
        String massage = studentService.updateStudent(studentRegDTO);
//      Registation number only add both column in table
        String massageHistory=studentService.saveStudentHistory(studentRegDTO);
        String massageSummery=studentService.saveStudentSummery(studentRegDTO);
        ResponseEntity<StandardResponce> response=
                new ResponseEntity<StandardResponce>(
                        new StandardResponce(201,"Updated",massageHistory+massageSummery)
                        ,HttpStatus.CREATED);
        return response;
    }
//    ================================================================================================================
    @DeleteMapping(path = "/deleteRegStudent/{studentRegNo}")
    public ResponseEntity<StandardResponce> deleteRegStudent(@PathVariable (value = "studentRegNo") String studentRegNo){
        String massage = studentService.deleteStudent(studentRegNo);
        ResponseEntity<StandardResponce> response=
                new ResponseEntity<StandardResponce>(
                        new StandardResponce(200,"Deleted",massage)
                        ,HttpStatus.OK);
        return response;
    }

    //Student Attendance Mark
    @PostMapping("/attendMark")
    public ResponseEntity<StandardResponce> AttendMark(@RequestBody StudentCurrentAttendDTO studentAttendDTO){
        String massage=studentService.attendMarkStudent(studentAttendDTO);
        ResponseEntity<StandardResponce> response=
                new ResponseEntity<StandardResponce>(
                        new StandardResponce(201,"Save",massage)
                        ,HttpStatus.CREATED);
        return response;
    }
//    ===========================================================================================
    @PostMapping("/acceptedAttendance")
    public ResponseEntity<StandardResponce> acceptedAttendance(@RequestBody StudentCurrentAttendDTO studentAttendDTO){
        String massage=studentService.acceptedAttendance(studentAttendDTO);
        String massageSummery=studentService.markAttendInSummery(studentAttendDTO);
        System.out.println(studentAttendDTO);
        ResponseEntity<StandardResponce> response=
                new ResponseEntity<StandardResponce>(
                        new StandardResponce(201,"Accepted",massage+massageSummery)
                        ,HttpStatus.CREATED);
        return response;
    }
//    ===========================================================================================

    @GetMapping("/getAllAcceptAttendance")
    public ResponseEntity<StandardResponce> getAllAcceptStudentAttend(){
        List<TotalAttendDTO> totalAttendDTO = studentService.getAllAcceptStudentAttend();
        ResponseEntity<StandardResponce> response=
                new ResponseEntity<StandardResponce>(
                        new StandardResponce(200,"Save",totalAttendDTO)
                        ,HttpStatus.OK);
        return response;
    }


    @GetMapping("/getAllAttendance")
    public ResponseEntity<StandardResponce> getAllStudentAttend(){
        List<StudentCurrentAttendDTO> studentAttendDTO = studentService.getAllStudentAttend();
        ResponseEntity<StandardResponce> response=
                new ResponseEntity<StandardResponce>(
                        new StandardResponce(200,"Save",studentAttendDTO)
                        ,HttpStatus.OK);
        return response;
    }

    @DeleteMapping(path = "/deleteAttendance/{studentRegNo}/{date}")
    public ResponseEntity<StandardResponce> deleteAttendance(@PathVariable (value = "studentRegNo") String studentRegNo,
                                   @PathVariable (value = "date") String date){
        String massage = studentService.deleteAttendance(studentRegNo,date);
        ResponseEntity<StandardResponce> response=
                new ResponseEntity<StandardResponce>(
                        new StandardResponce(200,"Deleted",massage)
                        ,HttpStatus.OK);
        return response;
    }

    @PostMapping("/addColumn")
    public ResponseEntity<StandardResponce> addColumn(@RequestParam String columnName) {
        // Pass the raw column name with slashes to the service layer
        String massage=studentService.addColumnToSummery(columnName);
        ResponseEntity<StandardResponce> response=
                new ResponseEntity<StandardResponce>(
                        new StandardResponce(201,"Created",massage)
                        ,HttpStatus.CREATED);
        return response;
    }

    @PostMapping("/deleteColumn")
    public ResponseEntity<StandardResponce> deleteColumn(@RequestParam String columnName) {
        // Pass the raw column name with slashes to the service layer
        String massage = studentService.deleteColumnFromSummery(columnName);
        ResponseEntity<StandardResponce> response=
                new ResponseEntity<StandardResponce>(
                        new StandardResponce(200,"Deleted",massage)
                        ,HttpStatus.OK);
        return response;
    }

    @GetMapping("/getAllSummeryData")
    public ResponseEntity<StandardResponce> getAllSummeryData() {
        List<Map<String, Object>> massage = studentService.getAllSummeryData(); // Fetch data from the service
        ResponseEntity<StandardResponce> response=
                new ResponseEntity<StandardResponce>(
                        new StandardResponce(200,"OK",massage)
                        ,HttpStatus.OK);
        return response;
    }
//    mobile app get data
    @GetMapping("/getAttendanceByRegNo/{regNo}")
    public ResponseEntity<StandardResponce> getAttendanceByRegNo(@PathVariable String regNo) {
        List<Map<String, Object>> massage=studentService.getAttendSummeryData(regNo);
        ResponseEntity<StandardResponce> response=
                new ResponseEntity<StandardResponce>(
                        new StandardResponce(201,"OK",massage)
                        ,HttpStatus.OK);
        return response;

    }


}
