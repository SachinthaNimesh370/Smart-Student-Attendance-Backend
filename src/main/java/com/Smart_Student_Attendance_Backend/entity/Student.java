package com.Smart_Student_Attendance_Backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "student_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    @Id
    @Column(name = "student_id",length = 10)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentId;

    @Column(name = "student_reg_no",length = 10)
    private String studentRegNo;

    @Column(name = "student_password",length = 100)
    private String studentPassword;

    @Column(name = "active_status")
    private boolean activestatus;

}
