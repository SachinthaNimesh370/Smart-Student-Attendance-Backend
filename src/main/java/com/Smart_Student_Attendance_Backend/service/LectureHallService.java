package com.Smart_Student_Attendance_Backend.service;

import com.Smart_Student_Attendance_Backend.dto.LectureHallsDTO;
import com.Smart_Student_Attendance_Backend.dto.ServiceResponceDTO;

public interface LectureHallService {
    ServiceResponceDTO savelecturehall(LectureHallsDTO lectureHallsDTO);

    ServiceResponceDTO updatelecturehall(LectureHallsDTO lectureHallsDTO);

    ServiceResponceDTO getAllLecturehall();

    ServiceResponceDTO deleteLecturehall(int id);
}
