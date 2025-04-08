package com.Smart_Student_Attendance_Backend.service;

import com.Smart_Student_Attendance_Backend.dto.NotificationDTO;
import com.Smart_Student_Attendance_Backend.dto.ServiceResponceDTO;

public interface NotificationService {
    ServiceResponceDTO createNotification(NotificationDTO notificationDTO);

    ServiceResponceDTO getAllNotification();

    ServiceResponceDTO updateNotification(NotificationDTO notificationDTO);

    ServiceResponceDTO deleteNotification(int id);
}
