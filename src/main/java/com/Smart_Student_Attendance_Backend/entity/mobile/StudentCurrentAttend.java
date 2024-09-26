package com.Smart_Student_Attendance_Backend.entity.mobile;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name = "student_attend")
@NoArgsConstructor
@AllArgsConstructor
@Data
@TypeDefs({
        //Hibernet Type 5 Dependancy magin
        @TypeDef(name = "json",typeClass = JsonType.class)
})

public class StudentCurrentAttend {
    @Id
    @Column(name = "student_reg_no",length = 10,nullable = false)
    private String studentRegNo;
    @Column(name = "time",length = 10,nullable = false)
    private String time;
    @Column(name = "date",length = 10,nullable = false)
    private String date;
    @Type(type = "json")
    @Column(name = "location",length = 10,columnDefinition = "json")
    private ArrayList<Double> location;
    @Column(name = "active_status",columnDefinition = "TINYINT default 1")
    private boolean attendance;

}
