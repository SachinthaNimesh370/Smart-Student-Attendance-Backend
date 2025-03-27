package com.Smart_Student_Attendance_Backend.service.mobile;

import com.Smart_Student_Attendance_Backend.dto.mobile.LectureHallsDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.ServiceResponceDTO;

public interface LectureHallService {
    ServiceResponceDTO savelecturehall(LectureHallsDTO lectureHallsDTO);

    ServiceResponceDTO updatelecturehall(LectureHallsDTO lectureHallsDTO);

    ServiceResponceDTO getAllLecturehall();

    ServiceResponceDTO deleteLecturehall(int id);
}
