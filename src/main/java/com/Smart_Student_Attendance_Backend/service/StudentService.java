package com.Smart_Student_Attendance_Backend.service;

import com.Smart_Student_Attendance_Backend.dto.ServiceResponceDTO;
import com.Smart_Student_Attendance_Backend.dto.StudentCurrentAttendDTO;
import com.Smart_Student_Attendance_Backend.dto.StudentRegDTO;
import com.Smart_Student_Attendance_Backend.dto.StudentSignInDTO;

public interface StudentService {

    ServiceResponceDTO saveStudent(StudentRegDTO studentRegDTO);

    ServiceResponceDTO signInService(StudentSignInDTO studentSignInDTO);

    ServiceResponceDTO attendMarkStudent(StudentCurrentAttendDTO studentAttendDTO);

    ServiceResponceDTO getAllStudent();


    ServiceResponceDTO updateStudentWithHistoryAndSummary(StudentRegDTO studentRegDTO);
    ServiceResponceDTO updateStudent(StudentRegDTO studentRegDTO);



    ServiceResponceDTO deleteStudent(String studentRegNo);

    ServiceResponceDTO getAllStudentAttend();

    ServiceResponceDTO deleteAttendance(String studentRegNo,String date);

    ServiceResponceDTO saveStudentHistory(StudentRegDTO studentRegDTO);

    ServiceResponceDTO acceptedAttendance(StudentCurrentAttendDTO studentAttendDTO);

    ServiceResponceDTO getAllAcceptStudentAttend();

    ServiceResponceDTO addColumnToSummery(String columnName);

    ServiceResponceDTO deleteColumnFromSummery(String columnName);
    ServiceResponceDTO getAllSummeryData();

    ServiceResponceDTO saveStudentSummery(StudentRegDTO studentRegDTO);

    ServiceResponceDTO markAttendInSummery(StudentCurrentAttendDTO studentcurrentAttendDTO);
    ServiceResponceDTO processAttendance(StudentCurrentAttendDTO studentAttendDTO);

    ServiceResponceDTO getAttendSummeryData(String regNo);

    ServiceResponceDTO getAttendanceCountsDayByDay();






}
