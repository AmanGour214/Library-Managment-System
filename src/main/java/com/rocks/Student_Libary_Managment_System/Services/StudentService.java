package com.rocks.Student_Libary_Managment_System.Services;

import com.rocks.Student_Libary_Managment_System.DTOs.StudentMoblieUpdateRequestDto;
import com.rocks.Student_Libary_Managment_System.Enums.CardStatus;
import com.rocks.Student_Libary_Managment_System.Modles.Card;
import com.rocks.Student_Libary_Managment_System.Modles.Student;
import com.rocks.Student_Libary_Managment_System.Repositorys.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String creatStudent(Student student){

        // basic attribute set by postMan

        // set all attritutt befor savig into data bases

        Card card =new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        // this variable hamne card me forigen key banayah or ye Student me primary key h;
        // is me student obj jayeng kyu ham ne card class me student class se join kiya h as a forigen ket
        card.setVariableNameofStudent(student);

        // hame student ke liye card genrate karna h
        // hamne student class me bidirectional mapping ki h card ke forigen key ke sat
        student.setCard(card);

        // at the last we have to save the student;
        studentRepository.save(student);

        return "student save sucessfully";
    }

    public String deletStudent(int id){

        studentRepository.deleteById(id);
        return "delet sucessfully";
    }

    public String findNameByEmail(String email){
         Student student=studentRepository.findByEmail(email);
         return student.getName();
    }

    public String updateMObileNo(StudentMoblieUpdateRequestDto studentReqDto){
// previous soln withOut dto

        //        // getting originalStudent because we only neet to update student
        //        Student originalStudent=studentRepository.findById(newStudent.getId()).get();
        //
        //        originalStudent.setMobileNo(newStudent.getMobileNo());
        //
        //        studentRepository.save(originalStudent);

        // now using dto

        Student originalStudent=studentRepository.findById(studentReqDto.getId()).get();
        originalStudent.setMobileNo(studentReqDto.getMobileNo());
        studentRepository.save(originalStudent);
        return "Student update sucessfully";

    }

}
