package com.Smart_Student_Attendance_Backend.repo.mobile;

import com.Smart_Student_Attendance_Backend.entity.mobile.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification,Integer> {
}
