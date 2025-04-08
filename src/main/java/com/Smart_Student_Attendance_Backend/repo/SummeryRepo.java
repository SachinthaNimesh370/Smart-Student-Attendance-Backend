package com.Smart_Student_Attendance_Backend.repo;

import com.Smart_Student_Attendance_Backend.entity.Summery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SummeryRepo extends JpaRepository<Summery,String> {

    List<Summery> findByStudentRegNo(String regNo);
    boolean existsByStudentRegNo(String regNo);
}
