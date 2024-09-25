package com.Smart_Student_Attendance_Backend.repo.mobile;

import com.Smart_Student_Attendance_Backend.entity.mobile.TotalAttend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TotalAttendRepo extends JpaRepository<TotalAttend,String> {
    boolean existsByStudentRegNoEquals(String studentRegNo);

    TotalAttend findByStudentRegNo(String studentRegNo);

}
