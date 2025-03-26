package com.Smart_Student_Attendance_Backend.service.mobile.impl;

import com.Smart_Student_Attendance_Backend.dto.mobile.*;
import com.Smart_Student_Attendance_Backend.entity.mobile.*;
import com.Smart_Student_Attendance_Backend.repo.mobile.*;
import com.Smart_Student_Attendance_Backend.service.mobile.StudentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.util.List;

@Service
@Transactional
public class StudentServiceIMPL implements StudentService {

    private final ModelMapper modelMapper;
    private final StudentRegRepo studentRegRepo;
    private final AttendMarkStudentRepo attendMarkStudentRepo;
    private final TotalAttendRepo totalAttendRepo;
    private final JdbcTemplate jdbcTemplate;
    private final SummeryRepo summeryRepo;
    private final NotificationRepo notificationRepo;
    private final LecturehallRepo lecturehallRepo;
    public StudentServiceIMPL(ModelMapper modelMapper,
                              StudentRegRepo studentRegRepo,
                              AttendMarkStudentRepo attendMarkStudentRepo,
                              TotalAttendRepo totalAttendRepo,
                              JdbcTemplate jdbcTemplate,
                              SummeryRepo summeryRepo,
                              NotificationRepo notificationRepo,
                              LecturehallRepo lecturehallRepo) {
        this.modelMapper = modelMapper;
        this.studentRegRepo = studentRegRepo;
        this.attendMarkStudentRepo = attendMarkStudentRepo;
        this.totalAttendRepo = totalAttendRepo;
        this.jdbcTemplate = jdbcTemplate;
        this.summeryRepo = summeryRepo;
        this.notificationRepo = notificationRepo;
        this.lecturehallRepo = lecturehallRepo;
    }
    @Override
    public ServiceResponceDTO saveStudent(StudentRegDTO studentRegDTO) {
        StudentReg studentReg = modelMapper.map(studentRegDTO, StudentReg.class);
        if(!studentRegRepo.existsByStudentRegNoEquals(studentReg.getStudentRegNo())){
            studentRegRepo.save(studentReg);
            return new ServiceResponceDTO(true,studentRegDTO.getStudentRegNo()+" Saved");
        }else {
            return new ServiceResponceDTO(false,studentRegDTO.getStudentRegNo()+"Alredy Added");
        }
    }

    @Override
    public ServiceResponceDTO signInService(StudentSignInDTO studentSignInDTO) {
        StudentReg studentReg = modelMapper.map(studentSignInDTO, StudentReg.class);
        if(studentRegRepo.existsByStudentRegNoEqualsAndStudentPasswordEqualsAndActivestatusEquals(studentReg.getStudentRegNo(),studentReg.getStudentPassword(),true)){
            return new ServiceResponceDTO(true,"Login Success");
        }else {
           return new ServiceResponceDTO(false,"Please Try Again");
        }
    }

    @Override
    public ServiceResponceDTO attendMarkStudent(StudentCurrentAttendDTO studentAttendDTO) {
        StudentCurrentAttend studentAttend = modelMapper.map(studentAttendDTO, StudentCurrentAttend.class);
        if(!attendMarkStudentRepo.existsByStudentRegNoEqualsAndDateEquals(studentAttend.getStudentRegNo(),studentAttend.getDate())){
            attendMarkStudentRepo.save(studentAttend);
            return new ServiceResponceDTO(true,"Successfully Mark Attendance");
        }else {
            return new ServiceResponceDTO(false,"Alredy Marked Attendance");
        }
    }

    @Override
    public ServiceResponceDTO getAllStudent() {
        List<StudentReg> getAllStudent=studentRegRepo.findAll();
        if(!getAllStudent.isEmpty()){
            List<StudentRegDTO> allStudent = modelMapper.map(getAllStudent,new TypeToken<List<StudentRegDTO>>(){}.getType());
            return new ServiceResponceDTO(true,allStudent);
        }else {
            return new ServiceResponceDTO(false,"Error");
        }
    }



    @Override
    public ServiceResponceDTO updateStudentWithHistoryAndSummary(StudentRegDTO studentRegDTO) {
        try {
            updateStudent(studentRegDTO);
            saveStudentHistory(studentRegDTO);
            saveStudentSummery(studentRegDTO);
            return new ServiceResponceDTO(true,"Update Success");
        } catch (Exception e) {
            return  new ServiceResponceDTO(false,"Transaction failed: " + e.getMessage());

        }
    }
    @Override
    public String updateStudent(StudentRegDTO studentRegDTO) {
        StudentReg studentReg = modelMapper.map(studentRegDTO,StudentReg.class);
        if(studentRegRepo.existsByStudentRegNoEquals(studentReg.getStudentRegNo())){
            studentRegRepo.save(studentReg);
            return "Update Success";
        }else {
            System.out.println(studentRegDTO);
            System.out.println(studentReg);
            return "Pleace Try Again";
        }
    }

    @Override
    public ServiceResponceDTO deleteStudent(String studentRegNo) {
        if(studentRegRepo.existsByStudentRegNoEquals(studentRegNo)){
            studentRegRepo.deleteByStudentRegNoEquals(studentRegNo);
            return new ServiceResponceDTO(true,"Delete Student");
        }
        else{
            return new ServiceResponceDTO(false,"No Data Found");
        }
    }

    @Override
    public ServiceResponceDTO getAllStudentAttend() {
        List<StudentCurrentAttend> getAllAttend=attendMarkStudentRepo.findAll();
        if(!getAllAttend.isEmpty()){
            List<StudentCurrentAttendDTO> getAllAttendance = modelMapper.map(getAllAttend,new TypeToken<List<StudentCurrentAttendDTO>>(){}.getType());
            return new ServiceResponceDTO(true,getAllAttendance);
        }else {
            return new ServiceResponceDTO(false,"No Data Found");
        }
    }



    @Override
    @Transactional

    public ServiceResponceDTO deleteAttendance(String studentRegNo,String date) {
        // Define a formatter that matches the format in the database
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Parse the incoming date (from PathVariable) into a LocalDate object
        LocalDate pathDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE); // Format from PathVariable (yyyy-MM-dd)
        String formattedDate = pathDate.format(formatter);

        if (attendMarkStudentRepo.existsByStudentRegNoEqualsAndDateEquals(studentRegNo,formattedDate)) {
            try {
                attendMarkStudentRepo.deleteByStudentRegNoEqualsAndDateEquals(studentRegNo,formattedDate);
                return new ServiceResponceDTO(true, "Delete Attendance");
            }catch (Exception e){
                return new ServiceResponceDTO(false, "Please Try Again "+e);
            }
        } else {
            return new ServiceResponceDTO(false,"No Data Found");
        }

    }

    @Override
    public String saveStudentHistory(StudentRegDTO studentRegDTO) {
        // Create a new TotalAttend object with only the studentRegNo
        TotalAttend totalAttend = new TotalAttend();
        totalAttend.setStudentRegNo(studentRegDTO.getStudentRegNo());
        totalAttend.setHistory(new ArrayList<>()); // Optionally initialize history as empty
        if(studentRegDTO.isActivestatus()){
            // Save the entity
            totalAttendRepo.save(totalAttend);
            return "Student attendance record created with regNo: " + studentRegDTO.getStudentRegNo();// Create a new TotalAttend object with only the studentRegNo
        }else{
            System.out.println("Not Active Student ");
            return "Not Active Student " ;
        }

    }

    @Override
    public String acceptedAttendance(StudentCurrentAttendDTO studentAttendDTO) {
        // Convert DTO to entity object
        StudentCurrentAttend studentAttend = modelMapper.map(studentAttendDTO, StudentCurrentAttend.class);

        // Check if the student with the given registration number exists
        if (totalAttendRepo.existsByStudentRegNoEquals(studentAttend.getStudentRegNo())) {

            // Fetch the existing TotalAttend entity from the database
            TotalAttend totalAttend = totalAttendRepo.findByStudentRegNo(studentAttend.getStudentRegNo());

            // Add the new StudentAttend data to the history list
            totalAttend.getHistory().add(studentAttend);

            // Save the updated TotalAttend entity
            totalAttendRepo.save(totalAttend);

            return "Save Success";
        } else {
            // If studentRegNo does not exist, handle it
            System.out.println(studentAttendDTO);
            System.out.println(studentAttend);
            return "Please Try Again";
        }
    }

    @Override
    public ServiceResponceDTO getAllAcceptStudentAttend() {
        List<TotalAttend> getAcceptAllAttend=totalAttendRepo.findAll();
        if(!getAcceptAllAttend.isEmpty()){
            List<TotalAttendDTO> totalAttendDTO = modelMapper.map(getAcceptAllAttend,new TypeToken<List<TotalAttendDTO>>(){}.getType());
            return new ServiceResponceDTO(true,totalAttendDTO);
        }else {
            return new ServiceResponceDTO(false,"No Registered Student");
        }
    }


    @Override
    public String addColumnToSummery(String columnName) {
        // Escape the column name by wrapping it with backticks (`) for MySQL
        String sql = "ALTER TABLE summery ADD COLUMN `" + columnName + "` VARCHAR(255)";

        // Execute the SQL query using your JdbcTemplate or any other query execution method
        jdbcTemplate.execute(sql);
        return "Created Column";
    }

    @Override
    public String deleteColumnFromSummery(String columnName) {
        // Escape the column name by wrapping it with backticks (`) for MySQL
        String sql = "ALTER TABLE summery DROP COLUMN `" + columnName + "`";

        // Execute the SQL query using your JdbcTemplate or any other query execution method
        jdbcTemplate.execute(sql);
        return "Deleted";
    }

    @Override
    // Method to fetch all data from the summery table dynamically
    public List<Map<String, Object>> getAllSummeryData() {
        String sql = "SELECT * FROM summery"; // Query to select all data
        return jdbcTemplate.queryForList(sql); // Returns a list of maps where each map represents a row
    }

    @Override
    public String saveStudentSummery(StudentRegDTO studentRegDTO) {
        // Create a new TotalAttend object with only the studentRegNo
        Summery summery = new Summery();
        summery.setStudentRegNo(studentRegDTO.getStudentRegNo());

        if(studentRegDTO.isActivestatus()){
            // Save the entity
            summeryRepo.save(summery);
            return "Student attendance record created with regNo: " + summery;// Create a new TotalAttend object with only the studentRegNo
        }else{
            System.out.println("Not Active Student ");
            return "Not Active Student " ;
        }
    }

    @Override
    public String markAttendInSummery(StudentCurrentAttendDTO studentCurrentAttendDTO) {
        String regNo = studentCurrentAttendDTO.getStudentRegNo();
        String date = studentCurrentAttendDTO.getDate();
        boolean attendance = true;

        System.out.println("Processing attendance: RegNo = " + regNo + ", Date = " + date + ", Attendance = " + attendance);

        try {
            // Check if the column for the given date exists
            String checkColumnSql = "SELECT column_name FROM information_schema.columns WHERE table_name = 'summery' AND column_name = ?";
            List<String> columns = jdbcTemplate.queryForList(checkColumnSql, new Object[]{date}, String.class);

            // If the column does not exist, add it
            if (columns.isEmpty()) {
                String addColumnSql = "ALTER TABLE summery ADD COLUMN `" + date + "` BOOLEAN";
                jdbcTemplate.execute(addColumnSql);
            }

            // Update the attendance for the student on the specified date
            String updateSql = "UPDATE summery SET `" + date + "` = ? WHERE student_reg_no = ?";
            int rowsAffected = jdbcTemplate.update(updateSql, attendance, regNo);

            // Return appropriate message based on update result
            if (rowsAffected > 0) {
                return "Attendance marked successfully for " + regNo + " on " + date.replace("_", "/");
            } else {
                return "Failed to mark attendance. Student with RegNo " + regNo + " not found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while marking attendance: " + e.getMessage();
        }
    }

    @Override
    public List<Map<String, Object>> getAttendSummeryData(String regNo) {
        // SQL query to select all data where student_reg_no matches the provided regNo
        String sql = "SELECT * FROM summery WHERE student_reg_no = ?";

        // Execute the query and pass the regNo as a parameter
        return jdbcTemplate.queryForList(sql, regNo);
    }


    @Override
    public List<Map<String, Object>> getAttendanceCountsDayByDay() {
        // Fetch all columns from the summery table dynamically in the order they are defined
        String sqlColumns = "SELECT column_name FROM information_schema.columns "
                + "WHERE table_name = 'summery' AND column_name != 'student_reg_no' "
                + "ORDER BY ordinal_position";  // This ensures the columns are retrieved in their original order

        List<String> columns = jdbcTemplate.queryForList(sqlColumns, String.class);

        List<Map<String, Object>> attendanceCounts = new ArrayList<>();

        // Iterate through each column to count `1` and `null`
        for (String column : columns) {
            String countSql = "SELECT COUNT(*) AS totalCount, "
                    + "SUM(CASE WHEN `" + column + "` = 1 THEN 1 ELSE 0 END) AS presentCount, "
                    + "SUM(CASE WHEN `" + column + "` IS NULL THEN 1 ELSE 0 END) AS absentCount "
                    + "FROM summery";

            Map<String, Object> countResult = jdbcTemplate.queryForMap(countSql);

            // Store results for this column in a map
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("columnName", column);
            resultMap.put("totalCount", countResult.get("totalCount"));
            resultMap.put("presentCount", countResult.get("presentCount"));
            resultMap.put("absentCount", countResult.get("absentCount"));

            attendanceCounts.add(resultMap); // Add the result to the list
        }

        return attendanceCounts; // Return the list of attendance counts in the correct order
    }

    @Override
    public String createNotification(NotificationDTO notificationDTO) {
        try {
            Notification notification = modelMapper.map(notificationDTO, Notification.class);
            notificationRepo.save(notification);
            return "Notification created successfully";
        } catch (Exception e) {
            return "Error while creating notification: " + e.getMessage();
        }
    }
    public void method1(){
        System.out.println("abc");
    }
    public int method2(){
        System.out.println("asd");
        return 0;

    }

    @Override
    public List<NotificationDTO> getAllNotification() {
        List<Notification> allNotification = notificationRepo.findAll();
        if (!allNotification.isEmpty()) {
            // Reverse the list so the last notification is first
            Collections.reverse(allNotification);

            // Map to DTOs
            List<NotificationDTO> getAllNotification = modelMapper.map(allNotification, new TypeToken<List<NotificationDTO>>(){}.getType());
            return getAllNotification;
        } else {
            throw new RuntimeException("Error");
        }
    }

    @Override
    public String updateNotification(NotificationDTO notificationDTO) {
        Notification notification = modelMapper.map(notificationDTO, Notification.class);
        notificationRepo.save(notification);
        return "Success Full Notification update";
    }

    @Override
    public String deleteNotification(int id) {
        notificationRepo.deleteById(id);
        return "Success Full Notification Delete";
    }

    @Override
    public String savelecturehall(LectureHallsDTO lectureHallsDTO) {
        LectureHalls lectureHalls= modelMapper.map(lectureHallsDTO, LectureHalls.class);
        lecturehallRepo.save(lectureHalls);
        return "Saved Lecture Halls";
    }

    @Override
    public String updatelecturehall(LectureHallsDTO lectureHallsDTO) {
        LectureHalls lectureHalls= modelMapper.map(lectureHallsDTO, LectureHalls.class);
        lecturehallRepo.save(lectureHalls);
        return "Saved Lecture Halls";
    }

    @Override
    public List<LectureHallsDTO> getAllLecturehall() {
        List<LectureHalls> alllectureHalls = lecturehallRepo.findAll();
        if (!alllectureHalls.isEmpty()) {


            // Map to DTOs
            List<LectureHallsDTO> getAlllectureHalls = modelMapper.map(alllectureHalls, new TypeToken<List<LectureHallsDTO>>(){}.getType());
            return getAlllectureHalls;
        } else {
            throw new RuntimeException("Error");
        }
    }

    @Override
    public String deleteLecturehall(int id) {
        lecturehallRepo.deleteById(id);
        return "Success Full Notification Delete";
    }


}
