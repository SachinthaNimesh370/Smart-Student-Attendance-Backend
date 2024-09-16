package com.Smart_Student_Attendance_Backend.service.mobile;

import com.Smart_Student_Attendance_Backend.dto.mobile.StudentRegDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentSignInDTO;

public interface StudentService {

    String saveStudent(StudentRegDTO studentRegDTO);

    String signInService(StudentSignInDTO studentSignInDTO);

}
