package com.Smart_Student_Attendance_Backend.entity.mobile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name = "summery")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Summery {
    @Id
    @Column(name = "student_reg_no",length = 10,nullable = false)
    private String studentRegNo;

}
