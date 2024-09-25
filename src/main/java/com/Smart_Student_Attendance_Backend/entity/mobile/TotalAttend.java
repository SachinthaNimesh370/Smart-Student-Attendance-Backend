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
import java.util.Objects;

@Entity
@Table(name = "total_attendance")
@NoArgsConstructor
@AllArgsConstructor
@Data
@TypeDefs({
        //Hibernet Type 5 Dependancy magin
        @TypeDef(name = "json",typeClass = JsonType.class)
})
public class TotalAttend {
    @Id
    @Column(name = "student_reg_no",length = 10,nullable = false)
    private String studentRegNo;

    @Type(type = "json")
    @Column(name = "history", columnDefinition = "json")
    private ArrayList<StudentAttend> history = new ArrayList<>();

}
