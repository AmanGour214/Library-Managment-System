package com.rocks.Student_Libary_Managment_System.Repositorys;

import com.rocks.Student_Libary_Managment_System.Modles.Transactions;
import jakarta.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Integer> {

    @Query(value = "select * from transaction where book_id=:bookId and card_id=:cardId and is_issu_operation=true",nativeQuery = true)
    List<Transactions>repoTransationsList(int bookId,int cardId);
}
