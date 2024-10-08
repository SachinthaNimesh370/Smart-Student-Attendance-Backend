package com.Smart_Student_Attendance_Backend.entity.mobile;


import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "lecture_halls")
@NoArgsConstructor
@AllArgsConstructor
@Data
@TypeDefs({
        //Hibernet Type 5 Dependancy magin
        @TypeDef(name = "json",typeClass = JsonType.class)
})
public class LectureHalls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 4,nullable = false)
    private int id;

    @Column(name = "hall_name",length = 10,nullable = false)
    private String hall_name;

    @Type(type = "json")
    @Column(name = "location",length = 10,columnDefinition = "json")
    private ArrayList<Double> location;

}
