package com.Smart_Student_Attendance_Backend.service.mobile;

import com.Smart_Student_Attendance_Backend.dto.mobile.StudentAttendDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentRegDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentSignInDTO;

import java.util.List;

public interface StudentService {

    String saveStudent(StudentRegDTO studentRegDTO);

    boolean signInService(StudentSignInDTO studentSignInDTO);

    String attendMarkStudent(StudentAttendDTO studentAttendDTO);

    List<StudentRegDTO> getAllStuden();


    String updateStudent(StudentRegDTO studentRegDTO);

}
