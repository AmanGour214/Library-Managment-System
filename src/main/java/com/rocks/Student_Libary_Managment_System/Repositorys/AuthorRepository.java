package com.rocks.Student_Libary_Managment_System.Repositorys;

import com.rocks.Student_Libary_Managment_System.Modles.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

}
