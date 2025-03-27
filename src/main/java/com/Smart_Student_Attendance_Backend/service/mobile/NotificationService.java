package com.Smart_Student_Attendance_Backend.service.mobile;

import com.Smart_Student_Attendance_Backend.dto.mobile.NotificationDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.ServiceResponceDTO;

public interface NotificationService {
    ServiceResponceDTO createNotification(NotificationDTO notificationDTO);

    ServiceResponceDTO getAllNotification();

    ServiceResponceDTO updateNotification(NotificationDTO notificationDTO);

    ServiceResponceDTO deleteNotification(int id);
}
