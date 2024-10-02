package com.Smart_Student_Attendance_Backend.utill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardResponce {
    private int code;
    private String message;
    private Object data;
}
