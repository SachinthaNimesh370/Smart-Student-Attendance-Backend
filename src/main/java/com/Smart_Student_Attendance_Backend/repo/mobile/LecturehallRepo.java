package com.Smart_Student_Attendance_Backend.repo.mobile;

import com.Smart_Student_Attendance_Backend.entity.mobile.LectureHalls;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturehallRepo extends JpaRepository<LectureHalls,Integer> {
}
