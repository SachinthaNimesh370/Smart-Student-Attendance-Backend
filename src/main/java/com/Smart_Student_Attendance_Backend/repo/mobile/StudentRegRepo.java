package com.Smart_Student_Attendance_Backend.repo.mobile;

import com.Smart_Student_Attendance_Backend.entity.mobile.StudentReg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface StudentRegRepo extends JpaRepository<StudentReg,Integer> {
    boolean existsByStudentRegNoEquals(String studentRegNo); //  Before Register Checking Alredy Exist Registation Number

    boolean existsByStudentRegNoEqualsAndStudentPasswordEqualsAndActivestatusEquals(String studentRegNo, String studentPassword,boolean b);

    void deleteByStudentRegNoEquals(String studentRegNo);

}
