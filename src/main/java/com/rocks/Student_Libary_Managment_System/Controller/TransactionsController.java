package com.rocks.Student_Libary_Managment_System.Controller;

import com.rocks.Student_Libary_Managment_System.DTOs.IssuBookRequestDto;
import com.rocks.Student_Libary_Managment_System.DTOs.ReturnBookAndFineDto;
import com.rocks.Student_Libary_Managment_System.Modles.Transactions;
import com.rocks.Student_Libary_Managment_System.Services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionsController {

    @Autowired
    TransactionsService transactionsService;

    @PostMapping("/issuBook")
    public String issuBook(@RequestBody IssuBookRequestDto issuBookRequestDto){

       try{
           return transactionsService.issueBook(issuBookRequestDto);
       }
       catch (Exception e){
           return e.getMessage();
       }
    }

    @GetMapping("/getTransInfo")
    public String getTransactionId(@RequestParam("bookId") int  bookId,@RequestParam("cardId") int cardId){
        return transactionsService.getTransactions(bookId,cardId);
    }

    @PostMapping("/returnBook_fine")
    public String returnBookAndFine (@RequestBody ReturnBookAndFineDto returnBookAndFineDto){
        try{
            return transactionsService.returnBookAndFine(returnBookAndFineDto);
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}
