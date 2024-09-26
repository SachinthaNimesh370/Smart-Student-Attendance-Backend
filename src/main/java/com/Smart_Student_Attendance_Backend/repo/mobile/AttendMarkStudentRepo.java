package com.Smart_Student_Attendance_Backend.repo.mobile;

import com.Smart_Student_Attendance_Backend.entity.mobile.StudentCurrentAttend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendMarkStudentRepo extends JpaRepository<StudentCurrentAttend,String> {
    boolean existsByStudentRegNoEquals(String studentRegNo);
    boolean existsByStudentRegNoEqualsAndDateEquals(String studentRegNo, String date);
    void deleteByStudentRegNoEqualsAndDateEquals(String studentRegNo,String date);

}
