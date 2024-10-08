package com.Smart_Student_Attendance_Backend.service.mobile;

import com.Smart_Student_Attendance_Backend.dto.mobile.*;

import java.util.List;
import java.util.Map;

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

    String addColumnToSummery(String columnName);

    String deleteColumnFromSummery(String columnName);
    public List<Map<String, Object>> getAllSummeryData();

    String saveStudentSummery(StudentRegDTO studentRegDTO);

    String markAttendInSummery(StudentCurrentAttendDTO studentcurrentAttendDTO);

    List<Map<String, Object>> getAttendSummeryData(String regNo);

    List<Map<String, Object>> getAttendanceCountsDayByDay();


    String createNotification(NotificationDTO notificationDTO);


    List<NotificationDTO> getAllNotification();

    String updateNotification(NotificationDTO notificationDTO);

    String deleteNotification(int id);

    String savelecturehall(LectureHallsDTO lectureHallsDTO);

}
