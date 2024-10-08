package com.Smart_Student_Attendance_Backend.entity.mobile;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "notification")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 10,nullable = false)
    private int id;

    @Column(name = "date",length = 10,nullable = false)
    private String date;

    @Column(name = "time",length = 10,nullable = false)
    private String time;

    @Column(name = "notification",length = 100,nullable = false)
    private String notification;
}
