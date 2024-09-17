package com.Smart_Student_Attendance_Backend.dto.mobile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentRegDTO {

    private String studentRegNo;
    private String studentEmail;
    private String studentPassword;
    private boolean activestatus;


}
