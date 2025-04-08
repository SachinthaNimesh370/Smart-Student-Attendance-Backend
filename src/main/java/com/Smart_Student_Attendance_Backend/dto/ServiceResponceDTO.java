package com.Smart_Student_Attendance_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ServiceResponceDTO {
    private boolean success;
    private Object object;
}
