package com.Smart_Student_Attendance_Backend.repo.mobile;

import com.Smart_Student_Attendance_Backend.entity.mobile.StudentAttend;
import com.Smart_Student_Attendance_Backend.entity.mobile.StudentReg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendMarkStudentRepo extends JpaRepository<StudentAttend,String> {
    boolean existsByStudentRegNoEquals(String studentRegNo);




}
