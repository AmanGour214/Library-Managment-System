package com.rocks.Student_Libary_Managment_System.Controller;

import com.rocks.Student_Libary_Managment_System.DTOs.StudentMoblieUpdateRequestDto;
import com.rocks.Student_Libary_Managment_System.Modles.Student;
import com.rocks.Student_Libary_Managment_System.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String creatStudent(@RequestBody Student student){


        return studentService.creatStudent(student);
    }
    @DeleteMapping("/deletbyid")
    public String deletById(@RequestParam("q") int id){
        return studentService.deletStudent(id);
    }

    @GetMapping("/get_user")
    public String getNameByEmail(@RequestParam("email") String email){
        return studentService.findNameByEmail(email);
    }
    @PutMapping("/update_mobileNo")
    public String updateMobileNo(@RequestBody StudentMoblieUpdateRequestDto studentMoblieUpdateRequestDto){
    return studentService.updateMObileNo(studentMoblieUpdateRequestDto);
    }

}
