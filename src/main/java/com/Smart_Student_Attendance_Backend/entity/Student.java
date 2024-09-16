package com.Smart_Student_Attendance_Backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    @Id
    @Column(name = "student_reg_no",length = 10)
    private int studentRegNo;
    @Column(name = "student_password",length = 100)
    private String studentPassword;

    @Column(name = "active_status")
    private boolean activestatus=false;

}
