package com.Smart_Student_Attendance_Backend.dto.mobile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentCurrentAttendDTO {
    private String studentRegNo;
    private String time;
    private String date;
    private ArrayList<Double> location;;
    private boolean attendance;
}
