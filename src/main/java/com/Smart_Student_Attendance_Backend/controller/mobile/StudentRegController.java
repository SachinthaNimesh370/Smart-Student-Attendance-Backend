package com.Smart_Student_Attendance_Backend.controller.mobile;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentCurrentAttendDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentRegDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentSignInDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.TotalAttendDTO;
import com.Smart_Student_Attendance_Backend.entity.mobile.Summery;
import com.Smart_Student_Attendance_Backend.service.mobile.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String SaveStudent(@RequestBody StudentRegDTO studentRegDTO){
        System.out.println("Reg No "+ studentRegDTO.getStudentRegNo());
        System.out.println("Email "+ studentRegDTO.getStudentEmail());
        System.out.println("Student password "+ studentRegDTO.getStudentPassword());
        System.out.println("Active Status "+ studentRegDTO.isActivestatus());
        String massage=studentService.saveStudent(studentRegDTO);
//        String massageHistory=studentService.saveStudentHistory(studentRegDTO.getStudentRegNo());
        return massage ;
    }

    //Student Sign IN
    @PostMapping("/signIn")
    public boolean SignIn(@RequestBody StudentSignInDTO studentSignInDTO){
        System.out.println("Reg No "+ studentSignInDTO.getStudentRegNo());
        System.out.println("Student password "+ studentSignInDTO.getStudentPassword());
        boolean massage=studentService.signInService(studentSignInDTO);

        return massage;

    }

    //Get All Register Student Data
    @GetMapping("/getAllStudent")
    public List<StudentRegDTO> getAllStudent(){
        List<StudentRegDTO> studentRegDTO = studentService.getAllStudent();
        return studentRegDTO;
    }
    @PutMapping("/updateRegStudent")
    public String updateStudent(@RequestBody StudentRegDTO studentRegDTO){
        String massage = studentService.updateStudent(studentRegDTO);
//      Registation number only add both column in table
        String massageHistory=studentService.saveStudentHistory(studentRegDTO);
        String massageSummery=studentService.saveStudentSummery(studentRegDTO);
        return massage+massageHistory+massageSummery;
    }
    @DeleteMapping(path = "/deleteRegStudent/{studentRegNo}")
    public String deleteRegStudent(@PathVariable (value = "studentRegNo") String studentRegNo){
        String massage = studentService.deleteStudent(studentRegNo);
        return massage;
    }

    //Student Attendance Mark
    @PostMapping("/attendMark")
    public String AttendMark(@RequestBody StudentCurrentAttendDTO studentAttendDTO){
        System.out.println("Reg No "+ studentAttendDTO.getStudentRegNo());
        System.out.println("Time "+ studentAttendDTO.getTime());
        System.out.println("Date "+ studentAttendDTO.getDate());
        System.out.println("Location "+ studentAttendDTO.getLocation());
        System.out.println("Attendance "+ studentAttendDTO.isAttendance());
        String massage=studentService.attendMarkStudent(studentAttendDTO);
        System.out.println(studentAttendDTO);
        return massage;
    }
    @PostMapping("/acceptedAttendance")
    public String acceptedAttendance(@RequestBody StudentCurrentAttendDTO studentAttendDTO){
        System.out.println("Reg No "+ studentAttendDTO.getStudentRegNo());
        System.out.println("Time "+ studentAttendDTO.getTime());
        System.out.println("Date "+ studentAttendDTO.getDate());
        System.out.println("Location "+ studentAttendDTO.getLocation());
        System.out.println("Attendance "+ studentAttendDTO.isAttendance());
        String massage=studentService.acceptedAttendance(studentAttendDTO);
        String massageSummery=studentService.markAttendInSummery(studentAttendDTO);
        System.out.println(studentAttendDTO);
        return massage+massageSummery;
    }

    @GetMapping("/getAllAcceptAttendance")
    public List<TotalAttendDTO> getAllAcceptStudentAttend(){
        List<TotalAttendDTO> totalAttendDTO = studentService.getAllAcceptStudentAttend();
        return totalAttendDTO;
    }


    @GetMapping("/getAllAttendance")
    public List<StudentCurrentAttendDTO> getAllStudentAttend(){
        List<StudentCurrentAttendDTO> studentAttendDTO = studentService.getAllStudentAttend();
        return studentAttendDTO;
    }

    @DeleteMapping(path = "/deleteAttendance/{studentRegNo}/{date}")
    public String deleteAttendance(@PathVariable (value = "studentRegNo") String studentRegNo,
                                   @PathVariable (value = "date") String date){
        System.out.println(studentRegNo+" "+date);
        String massage = studentService.deleteAttendance(studentRegNo,date);
        System.out.println(studentRegNo+" "+date);

        return massage;
    }

    @PostMapping("/addColumn")
    public String addColumn(@RequestParam String columnName) {
        // Pass the raw column name with slashes to the service layer
        studentService.addColumnToSummery(columnName);
        return "Column added successfully!";
    }

    @PostMapping("/deleteColumn")
    public String deleteColumn(@RequestParam String columnName) {
        // Pass the raw column name with slashes to the service layer
        studentService.deleteColumnFromSummery(columnName);
        return "Column deleted successfully!";
    }

    @GetMapping("/getAllSummeryData")
    public List<Map<String, Object>> getAllSummeryData() {
        return studentService.getAllSummeryData(); // Fetch data from the service
    }
//    mobile app get data
    @GetMapping("/getAttendanceByRegNo/{regNo}")
    public List<Map<String, Object>> getAttendanceByRegNo(@PathVariable String regNo) {
        List<Map<String, Object>> massage=studentService.getAttendSummeryData(regNo);
        return massage; // Fetch data from the service

    }


}
