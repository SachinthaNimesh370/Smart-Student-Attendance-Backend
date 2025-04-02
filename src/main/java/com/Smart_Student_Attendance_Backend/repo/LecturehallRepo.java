package com.Smart_Student_Attendance_Backend.repo;

import com.Smart_Student_Attendance_Backend.entity.LectureHalls;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturehallRepo extends JpaRepository<LectureHalls,Integer> {
}
