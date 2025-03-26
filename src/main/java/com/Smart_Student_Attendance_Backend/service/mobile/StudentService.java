package com.Smart_Student_Attendance_Backend.service.mobile;

import com.Smart_Student_Attendance_Backend.dto.mobile.*;

import java.util.List;
import java.util.Map;

public interface StudentService {

    ServiceResponceDTO saveStudent(StudentRegDTO studentRegDTO);

    ServiceResponceDTO signInService(StudentSignInDTO studentSignInDTO);

    ServiceResponceDTO attendMarkStudent(StudentCurrentAttendDTO studentAttendDTO);

    ServiceResponceDTO getAllStudent();


    ServiceResponceDTO updateStudentWithHistoryAndSummary(StudentRegDTO studentRegDTO);
    String updateStudent(StudentRegDTO studentRegDTO);



    ServiceResponceDTO deleteStudent(String studentRegNo);

    ServiceResponceDTO getAllStudentAttend();

    ServiceResponceDTO deleteAttendance(String studentRegNo,String date);

    ServiceResponceDTO saveStudentHistory(StudentRegDTO studentRegDTO);

    String acceptedAttendance(StudentCurrentAttendDTO studentAttendDTO);

    ServiceResponceDTO getAllAcceptStudentAttend();

    ServiceResponceDTO addColumnToSummery(String columnName);

    ServiceResponceDTO deleteColumnFromSummery(String columnName);
    ServiceResponceDTO getAllSummeryData();

    String saveStudentSummery(StudentRegDTO studentRegDTO);

    String markAttendInSummery(StudentCurrentAttendDTO studentcurrentAttendDTO);

    ServiceResponceDTO getAttendSummeryData(String regNo);

    ServiceResponceDTO getAttendanceCountsDayByDay();


    ServiceResponceDTO createNotification(NotificationDTO notificationDTO);


    ServiceResponceDTO getAllNotification();

    String updateNotification(NotificationDTO notificationDTO);

    String deleteNotification(int id);

    String savelecturehall(LectureHallsDTO lectureHallsDTO);

    String updatelecturehall(LectureHallsDTO lectureHallsDTO);

    List<LectureHallsDTO> getAllLecturehall();

    String deleteLecturehall(int id);

}
