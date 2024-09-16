package com.Smart_Student_Attendance_Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SmartStudentAttendanceBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartStudentAttendanceBackendApplication.class, args);
	}

}
