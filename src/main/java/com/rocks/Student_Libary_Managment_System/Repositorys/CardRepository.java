package com.rocks.Student_Libary_Managment_System.Repositorys;

import com.rocks.Student_Libary_Managment_System.Modles.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

}
