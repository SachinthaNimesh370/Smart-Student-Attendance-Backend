package com.Smart_Student_Attendance_Backend.service.mobile.impl;

import com.Smart_Student_Attendance_Backend.dto.mobile.StudentCurrentAttendDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentRegDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentSignInDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.TotalAttendDTO;
import com.Smart_Student_Attendance_Backend.entity.mobile.StudentCurrentAttend;
import com.Smart_Student_Attendance_Backend.entity.mobile.StudentReg;
import com.Smart_Student_Attendance_Backend.entity.mobile.TotalAttend;
import com.Smart_Student_Attendance_Backend.repo.mobile.AttendMarkStudentRepo;
import com.Smart_Student_Attendance_Backend.repo.mobile.StudentRegRepo;
import com.Smart_Student_Attendance_Backend.repo.mobile.TotalAttendRepo;
import com.Smart_Student_Attendance_Backend.service.mobile.StudentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceIMPL implements StudentService {
    @Autowired
    public ModelMapper modelMapper;
    @Autowired
    private StudentRegRepo studentRegRepo;
    @Autowired
    private AttendMarkStudentRepo attendMarkStudentRepo;
    @Autowired
    private TotalAttendRepo totalAttendRepo;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public String saveStudent(StudentRegDTO studentRegDTO) {
        StudentReg studentReg = modelMapper.map(studentRegDTO, StudentReg.class);
        if(!studentRegRepo.existsByStudentRegNoEquals(studentReg.getStudentRegNo())){
            studentRegRepo.save(studentReg);
            return studentRegDTO.getStudentRegNo()+" Saved";
        }else {
            return"Alredy Added";
        }
    }

    @Override
    public boolean signInService(StudentSignInDTO studentSignInDTO) {
        StudentReg studentReg = modelMapper.map(studentSignInDTO, StudentReg.class);
        if(studentRegRepo.existsByStudentRegNoEqualsAndStudentPasswordEqualsAndActivestatusEquals(studentReg.getStudentRegNo(),studentReg.getStudentPassword(),true)){
            return true;
        }else {
           return false;
        }
    }

    @Override
    public String attendMarkStudent(StudentCurrentAttendDTO studentAttendDTO) {
        StudentCurrentAttend studentAttend = modelMapper.map(studentAttendDTO, StudentCurrentAttend.class);
        if(!attendMarkStudentRepo.existsByStudentRegNoEqualsAndDateEquals(studentAttend.getStudentRegNo(),studentAttend.getDate())){
            attendMarkStudentRepo.save(studentAttend);
            return "Save Success";
        }else {
            System.out.println(studentAttendDTO);
            System.out.println(studentAttend);
            return "Alredy Marked Attendance";
        }
    }

    @Override
    public List<StudentRegDTO> getAllStudent() {
        List<StudentReg> getAllStudent=studentRegRepo.findAll();
        if(!getAllStudent.isEmpty()){
            List<StudentRegDTO> allStudent = modelMapper.map(getAllStudent,new TypeToken<List<StudentRegDTO>>(){}.getType());
            return allStudent;
        }else {
            throw new RuntimeException("Error");
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
    @Transactional
    public String deleteStudent(String studentRegNo) {
        if(studentRegRepo.existsByStudentRegNoEquals(studentRegNo)){
            studentRegRepo.deleteByStudentRegNoEquals(studentRegNo);
            return "Sucssesful Delete Student";
        }
        else{
            throw new RuntimeException("No Data Found");
        }
    }

    @Override
    public List<StudentCurrentAttendDTO> getAllStudentAttend() {
        List<StudentCurrentAttend> getAllAttend=attendMarkStudentRepo.findAll();
        if(!getAllAttend.isEmpty()){
            List<StudentCurrentAttendDTO> getAllAttendance = modelMapper.map(getAllAttend,new TypeToken<List<StudentCurrentAttendDTO>>(){}.getType());
            return getAllAttendance;
        }else {
            throw new RuntimeException("Error");
        }
    }



    @Override
    @Transactional

    public String deleteAttendance(String studentRegNo,String date) {
        // Define a formatter that matches the format in the database
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Parse the incoming date (from PathVariable) into a LocalDate object
        LocalDate pathDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE); // Format from PathVariable (yyyy-MM-dd)
        String formattedDate = pathDate.format(formatter);


        if (attendMarkStudentRepo.existsByStudentRegNoEqualsAndDateEquals(studentRegNo,formattedDate)) {
            attendMarkStudentRepo.deleteByStudentRegNoEqualsAndDateEquals(studentRegNo,formattedDate);
            return "Successful Delete Attendance ";
        } else {
            System.out.println(attendMarkStudentRepo.findAll());
            return " on date: " + studentRegNo;
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
    public List<TotalAttendDTO> getAllAcceptStudentAttend() {
        List<TotalAttend> getAcceptAllAttend=totalAttendRepo.findAll();
        if(!getAcceptAllAttend.isEmpty()){
            List<TotalAttendDTO> totalAttendDTO = modelMapper.map(getAcceptAllAttend,new TypeToken<List<TotalAttendDTO>>(){}.getType());
            return totalAttendDTO;
        }else {
            throw new RuntimeException("Error");
        }
    }

//    @Override
//    public void addColumnToSummery(String columnName) {
//        String sql = String.format("ALTER TABLE summery ADD COLUMN %s BOOLEAN DEFAULT false", columnName);
//        jdbcTemplate.execute(sql);
//
//    }
@Override
public void addColumnToSummery(String columnName) {
    // Escape the column name by wrapping it with backticks (`) for MySQL
    String sql = "ALTER TABLE summery ADD COLUMN `" + columnName + "` VARCHAR(255)";

    // Execute the SQL query using your JdbcTemplate or any other query execution method
    jdbcTemplate.execute(sql);
}


}
