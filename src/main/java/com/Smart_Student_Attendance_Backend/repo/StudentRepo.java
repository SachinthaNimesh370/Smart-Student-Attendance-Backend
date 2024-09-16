package com.Smart_Student_Attendance_Backend.repo;

import com.Smart_Student_Attendance_Backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface StudentRepo extends JpaRepository<Student,Integer> {


    boolean existsByStudentRegNoEquals(String studentRegNo); //  Before Register Checking Alredy Exist Registation Number


}
