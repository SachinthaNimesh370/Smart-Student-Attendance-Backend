package com.Smart_Student_Attendance_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationDTO {

    private int id;
    private String date;
    private String time;
    private String notification;
}
