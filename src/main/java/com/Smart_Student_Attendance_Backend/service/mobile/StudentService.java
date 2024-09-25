package com.Smart_Student_Attendance_Backend.service.mobile;

import com.Smart_Student_Attendance_Backend.dto.mobile.StudentAttendDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentRegDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentSignInDTO;

import java.util.List;

public interface StudentService {

    String saveStudent(StudentRegDTO studentRegDTO);

    boolean signInService(StudentSignInDTO studentSignInDTO);

    String attendMarkStudent(StudentAttendDTO studentAttendDTO);

    List<StudentRegDTO> getAllStudent();


    String updateStudent(StudentRegDTO studentRegDTO);


    String deleteStudent(String studentRegNo);

    List<StudentAttendDTO> getAllStudentAttend();

    String deleteAttendance(String studentRegNo,String date);

    String saveStudentHistory(String studentRegNo);

    String acceptedAttendance(StudentAttendDTO studentAttendDTO);

}
