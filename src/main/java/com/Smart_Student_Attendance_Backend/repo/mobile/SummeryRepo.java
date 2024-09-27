package com.Smart_Student_Attendance_Backend.repo.mobile;

import com.Smart_Student_Attendance_Backend.entity.mobile.Summery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SummeryRepo extends JpaRepository<Summery,String> {

    List<Summery> findByStudentRegNo(String regNo);
}
