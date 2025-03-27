package com.Smart_Student_Attendance_Backend.service.mobile.impl;

import com.Smart_Student_Attendance_Backend.dto.mobile.NotificationDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.ServiceResponceDTO;
import com.Smart_Student_Attendance_Backend.entity.mobile.Notification;
import com.Smart_Student_Attendance_Backend.repo.mobile.NotificationRepo;
import com.Smart_Student_Attendance_Backend.service.mobile.NotificationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class NotificationServiceIMPL implements NotificationService {
    private final NotificationRepo notificationRepo;
    private final ModelMapper modelMapper;

    public NotificationServiceIMPL(NotificationRepo notificationRepo, ModelMapper modelMapper) {
        this.notificationRepo = notificationRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ServiceResponceDTO createNotification(NotificationDTO notificationDTO) {
        try {
            Notification notification = modelMapper.map(notificationDTO, Notification.class);
            notificationRepo.save(notification);
            return new ServiceResponceDTO(true,"Notification Created Successfully");
        } catch (Exception e) {
            return new ServiceResponceDTO(false,"Error While Creating Notification: " + e.getMessage());
        }
    }

    @Override
    public ServiceResponceDTO getAllNotification() {
        List<Notification> allNotification = notificationRepo.findAll();
        if (!allNotification.isEmpty()) {
            Collections.reverse(allNotification);
            List<NotificationDTO> getAllNotification = modelMapper.map(allNotification, new TypeToken<List<NotificationDTO>>(){}.getType());
            return new ServiceResponceDTO(true,getAllNotification);
        } else {
            return new ServiceResponceDTO(false,"Please Try Again");
        }
    }

    @Override
    public ServiceResponceDTO updateNotification(NotificationDTO notificationDTO) {
        Notification notification = modelMapper.map(notificationDTO, Notification.class);
        try {
            notificationRepo.save(notification);
            return new ServiceResponceDTO(true,"Success Full Notification update");
        }catch (Exception e){
            return  new ServiceResponceDTO(false,e.getMessage());
        }
    }

    @Override
    public ServiceResponceDTO deleteNotification(int id) {
        try {
            notificationRepo.deleteById(id);
            return new ServiceResponceDTO(true,"Success Full Notification Delete");
        }catch (Exception e){
            return new ServiceResponceDTO(false,"Please Try Again "+e.getMessage());
        }
    }
}
