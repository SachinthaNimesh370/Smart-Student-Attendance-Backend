package com.Smart_Student_Attendance_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {


    private String studentRegNo;
    private String studentPassword;
    private boolean activestatus;

}
