package com.Smart_Student_Attendance_Backend.dto.mobile;

import com.Smart_Student_Attendance_Backend.entity.mobile.StudentAttend;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TotalAttendDTO {
    private String studentRegNo;
    private ArrayList<StudentAttend> history = new ArrayList<>();
}
