package com.Smart_Student_Attendance_Backend.service.mobile;

import com.Smart_Student_Attendance_Backend.dto.mobile.*;

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
