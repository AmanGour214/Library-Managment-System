package com.rocks.Student_Libary_Managment_System.Repositorys;

import com.rocks.Student_Libary_Managment_System.Modles.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    /* I am using 2nd way to get the user
    *  i.e  its Inbuild+define them
    *  findBy[Any attribut name of the Entity]*/
    // this functions return objects
    Student findByEmail(String email);

    //want to find List of Student

    // select * from student where country=india;
     List<Student>findByCountry(String countyr);


}
