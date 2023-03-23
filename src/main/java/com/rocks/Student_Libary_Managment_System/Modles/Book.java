package com.rocks.Student_Libary_Managment_System.Modles;

import com.rocks.Student_Libary_Managment_System.Enums.Genre;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Book_db")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int pages;
    private boolean issued;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    // book is child for Author
    // setting foregin key;
    @ManyToOne
    @JoinColumn // give the name of column or by default it will take primary key of parent ;
    private Author authorInBook;

    @ManyToOne
    @JoinColumn
    private Card cardInBook;


    //  Book is parent for Transaction table;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Transactions> transactionsList=new ArrayList<>();// book me transition ka obj h or transation me book ka ;




    public Book() {
    }


    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthorInBook() {
        return authorInBook;
    }

    public void setAuthorInBook(Author authorInBook) {
        this.authorInBook = authorInBook;
    }

    public Card getCardInBook() {
        return cardInBook;
    }

    public void setCardInBook(Card cardInBook) {
        this.cardInBook = cardInBook;
    }


}
