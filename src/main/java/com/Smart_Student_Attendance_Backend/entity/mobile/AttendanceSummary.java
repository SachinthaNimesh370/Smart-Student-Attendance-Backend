package com.Smart_Student_Attendance_Backend.entity.mobile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "attendance_summary")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class AttendanceSummary {

    @Id
    @Column(name = "date", nullable = false)
    private String date;

    @ManyToOne
    @JoinColumn(name = "student_reg_no", nullable = false)
    private StudentReg studentReg;  // This links the student from StudentReg table


    @Column(name = "attendance_status", nullable = false)
    private boolean attendanceStatus;  // true for present, false for absent
}

