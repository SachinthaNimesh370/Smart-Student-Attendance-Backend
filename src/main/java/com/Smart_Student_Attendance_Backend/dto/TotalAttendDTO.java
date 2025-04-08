package com.Smart_Student_Attendance_Backend.dto;

import com.Smart_Student_Attendance_Backend.entity.StudentCurrentAttend;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TotalAttendDTO {
    private String studentRegNo;
    private ArrayList<StudentCurrentAttend> history = new ArrayList<>();
}
