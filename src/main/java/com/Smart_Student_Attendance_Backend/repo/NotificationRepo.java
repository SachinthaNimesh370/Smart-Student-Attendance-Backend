package com.Smart_Student_Attendance_Backend.repo;

import com.Smart_Student_Attendance_Backend.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification,Integer> {
}
