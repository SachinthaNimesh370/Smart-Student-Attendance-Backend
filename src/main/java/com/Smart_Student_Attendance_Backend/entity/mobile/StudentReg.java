package com.Smart_Student_Attendance_Backend.entity.mobile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "student_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentReg {
    @Id
    @Column(name = "student_reg_no",length = 10,nullable = false)
    private String studentRegNo;

    @Column(name = "student_Email",length = 100,nullable = false)
    private String studentEmail;

    @Column(name = "student_password",length = 100,nullable = false)
    private String studentPassword;

    @Column(name = "active_status",columnDefinition = "TINYINT default 1")
    private boolean activestatus;

}
