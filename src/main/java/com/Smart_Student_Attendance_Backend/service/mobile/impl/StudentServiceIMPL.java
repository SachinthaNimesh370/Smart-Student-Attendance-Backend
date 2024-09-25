package com.Smart_Student_Attendance_Backend.service.mobile.impl;

import com.Smart_Student_Attendance_Backend.dto.mobile.StudentAttendDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentRegDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.StudentSignInDTO;
import com.Smart_Student_Attendance_Backend.dto.mobile.TotalAttendDTO;
import com.Smart_Student_Attendance_Backend.entity.mobile.StudentAttend;
import com.Smart_Student_Attendance_Backend.entity.mobile.StudentReg;
import com.Smart_Student_Attendance_Backend.entity.mobile.TotalAttend;
import com.Smart_Student_Attendance_Backend.repo.mobile.AttendMarkStudentRepo;
import com.Smart_Student_Attendance_Backend.repo.mobile.StudentRegRepo;
import com.Smart_Student_Attendance_Backend.repo.mobile.TotalAttendRepo;
import com.Smart_Student_Attendance_Backend.service.mobile.StudentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    public String attendMarkStudent(StudentAttendDTO studentAttendDTO) {
        StudentAttend studentAttend = modelMapper.map(studentAttendDTO,StudentAttend.class);
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
    public List<StudentAttendDTO> getAllStudentAttend() {
        List<StudentAttend> getAllAttend=attendMarkStudentRepo.findAll();
        if(!getAllAttend.isEmpty()){
            List<StudentAttendDTO> getAllAttendance = modelMapper.map(getAllAttend,new TypeToken<List<StudentAttendDTO>>(){}.getType());
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
    public String saveStudentHistory(String studentRegNo) {
        // Create a new TotalAttend object with only the studentRegNo
        TotalAttend totalAttend = new TotalAttend();
        totalAttend.setStudentRegNo(studentRegNo);
        totalAttend.setHistory(new ArrayList<>()); // Optionally initialize history as empty

        // Save the entity
        totalAttendRepo.save(totalAttend);

        return "Student attendance record created with regNo: " + studentRegNo;// Create a new TotalAttend object with only the studentRegNo

    }

    @Override
    public String acceptedAttendance(StudentAttendDTO studentAttendDTO) {
        // Convert DTO to entity object
        StudentAttend studentAttend = modelMapper.map(studentAttendDTO, StudentAttend.class);

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


}
