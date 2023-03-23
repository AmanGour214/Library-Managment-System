package com.rocks.Student_Libary_Managment_System.Services;

import com.rocks.Student_Libary_Managment_System.DTOs.IssuBookRequestDto;
import com.rocks.Student_Libary_Managment_System.DTOs.ReturnBookAndFineDto;
import com.rocks.Student_Libary_Managment_System.Enums.CardStatus;
import com.rocks.Student_Libary_Managment_System.Enums.TransactionStatus;
import com.rocks.Student_Libary_Managment_System.Modles.Book;
import com.rocks.Student_Libary_Managment_System.Modles.Card;
import com.rocks.Student_Libary_Managment_System.Modles.Transactions;
import com.rocks.Student_Libary_Managment_System.Repositorys.BookRepository;
import com.rocks.Student_Libary_Managment_System.Repositorys.CardRepository;
import com.rocks.Student_Libary_Managment_System.Repositorys.TransactionsRepository;
import jakarta.servlet.ServletOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionsService {

    @Autowired
    TransactionsRepository transactionsRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    BookRepository bookRepository;

    public String issueBook(IssuBookRequestDto issuBookRequestDto) throws Exception {


        Card card = cardRepository.findById(issuBookRequestDto.getCardId()).get();
        Book book = bookRepository.findById(issuBookRequestDto.getBookId()).get();


        Transactions transactions = new Transactions();
        // setting its attribute;
        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setIssuOperation(true);
        transactions.setTransactionId(UUID.randomUUID().toString());
        transactions.setTransactionStatus(TransactionStatus.PENDING);

        // attribute left or valided check

        if (book == null || book.isIssued() == true) {
            // because agar book present hi nahi h of agar vo kisi ne phele se hi issue kiya h to issu nahi kar sakte;
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionsRepository.save(transactions);
            throw new Exception("book is not avaliable ");

        }
        if (card == null || (card.getCardStatus() != CardStatus.ACTIVATED)) {
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionsRepository.save(transactions);
            throw new Exception("card is not avaliable ");

        }

        // if above validation is not full fill then we definatly issue a book
        transactions.setTransactionStatus(TransactionStatus.SUCCESS);
        book.setIssued(true);

        // between the book and transaction : biDirection;

        List<Transactions> currTrans = book.getTransactionsList();
        currTrans.add(transactions);
        book.setTransactionsList(currTrans);

        // i need to make change in card also
        // book and card
        List<Book> currBooks = card.getBooksIssuWithCard();
        currBooks.add(book);
        card.setBooksIssuWithCard(currBooks);


        // card and the transaction;
        List<Transactions> cardCurrTrans = card.getListOfTransaction();
        cardCurrTrans.add(transactions);
        card.setListOfTransaction(cardCurrTrans);

        // saving the parent due to cascading effect child will automaticly save;
        cardRepository.save(card);


        return "book is issue success fully ";
    }

    public String getTransactions(int bookId,int cardId){

        List<Transactions>transactionsList=transactionsRepository.repoTransationsList(bookId,cardId);
        String transactionId= transactionsList.get(0).getTransactionId();
        return transactionId;
    }

    public String returnBookAndFine (ReturnBookAndFineDto returnBookAndFineDto) throws Exception{

        int bookId=returnBookAndFineDto.getBookId();
        int cardId=returnBookAndFineDto.getCardId();

        // getting actual entity;
        Book book=bookRepository.findById(bookId).get();
        Card card=cardRepository.findById(cardId).get();


        Transactions transactions = new Transactions();
        // attribute setting

        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setIssuOperation(false); // false means return the book;
        transactions.setTransactionStatus(TransactionStatus.PENDING);
        transactions.setTransactionId(UUID.randomUUID().toString());

        // validation check;
        if(book==null|| book.isIssued()==false){

            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionsRepository.save(transactions);
            throw new Exception("Book is Not issue Yet");
        }

        // this is for card
        if(card==null||card.getCardStatus()!=CardStatus.ACTIVATED){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionsRepository.save(transactions);
        }

        transactions.setTransactionStatus(TransactionStatus.SUCCESS);
        // we have to set issuBook is false because its return book
        // we are here on this line so definatly book is issue previouslly now i am returning that book
        book.setIssued(false);

        // between the book and transaction : biDirection;
        List<Transactions> currTrans = book.getTransactionsList();
        currTrans.add(transactions);
        book.setTransactionsList(currTrans);

        // we have to delet that book
        List<Book>currBook=card.getBooksIssuWithCard();
        currBook.remove(book);
        card.setBooksIssuWithCard(currBook);

        // between transation for card
        List<Transactions> cardCurrTrans = card.getListOfTransaction();
        cardCurrTrans.add(transactions);
        card.setListOfTransaction(cardCurrTrans);

        // calculating  the fine;

        List<Transactions>listOfTrans=transactionsRepository.repoTransationsList(bookId,cardId);

        Transactions transactions1=new Transactions();
        for(Transactions trans :listOfTrans){
            if(trans.getBook().equals(book) && (trans.getTransactionStatus()==TransactionStatus.SUCCESS)){
                transactions1=trans;
            }
        }
        LocalDate issueDate = new java.sql.Date( transactions1.getUpdateOn().getTime() ).toLocalDate();
        LocalDate returnDate=LocalDate.now();
        // it will calculate days between two dates;
        long days = ChronoUnit.DAYS.between(issueDate, returnDate);

        if(days>=15){
            days=days*15;
        }
        // saving parent;
        cardRepository.save(card);

        return "Book return sucsfully and thair fine ="+days;

    }
 }
