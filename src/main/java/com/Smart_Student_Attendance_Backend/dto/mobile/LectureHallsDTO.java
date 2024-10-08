package com.Smart_Student_Attendance_Backend.dto.mobile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LectureHallsDTO {
    private int id;
    private String hall_name;
    private ArrayList<Double> location;
}
