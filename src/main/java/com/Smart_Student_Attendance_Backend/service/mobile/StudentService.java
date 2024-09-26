package com.Smart_Student_Attendance_Backend.service.mobile;

import com.Smart_Student_Attendance_Backend.dto.mobile.StudentCurrentAttendDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentRegDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentSignInDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.TotalAttendDTO;

import java.util.List;

public interface StudentService {

    String saveStudent(StudentRegDTO studentRegDTO);

    boolean signInService(StudentSignInDTO studentSignInDTO);

    String attendMarkStudent(StudentCurrentAttendDTO studentAttendDTO);

    List<StudentRegDTO> getAllStudent();


    String updateStudent(StudentRegDTO studentRegDTO);


    String deleteStudent(String studentRegNo);

    List<StudentCurrentAttendDTO> getAllStudentAttend();

    String deleteAttendance(String studentRegNo,String date);

    String saveStudentHistory(StudentRegDTO studentRegDTO);

    String acceptedAttendance(StudentCurrentAttendDTO studentAttendDTO);

    List<TotalAttendDTO> getAllAcceptStudentAttend();

    void addColumnToSummery(String columnName);


}
