package com.rocks.Student_Libary_Managment_System.Modles;

import com.rocks.Student_Libary_Managment_System.Enums.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.repository.cdi.Eager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Card_DB")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp//Auto timestam the time when and entry is creted;
    private Date createdOn; // we are adding util libary it has hour :minut:second:Milisecond
    @UpdateTimestamp// Auto update time stam when the entry is update ;
    private Date updatedOn;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    // doing mapping

    @OneToOne
    @JoinColumn
    private Student variableNameofStudent;

    //card is parent for books ke liye
    @OneToMany(mappedBy = "cardInBook",cascade = CascadeType.ALL)
    private List<Book>booksIssuWithCard=new ArrayList<>();


    // card is parent for Transation table
    // one card many transations
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Transactions>listOfTransaction=new ArrayList<>();




    public Card() {
    }

    public List<Transactions> getListOfTransaction() {
        return listOfTransaction;
    }

    public void setListOfTransaction(List<Transactions> listOfTransaction) {
        this.listOfTransaction = listOfTransaction;
    }

    public List<Book> getBooksIssuWithCard() {
        return booksIssuWithCard;
    }

    public void setBooksIssuWithCard(List<Book> booksIssuWithCard) {
        this.booksIssuWithCard = booksIssuWithCard;
    }

    public Student getVariableNameofStudent() {
        return variableNameofStudent;
    }

    public void setVariableNameofStudent(Student variableNameofStudent) {
        this.variableNameofStudent = variableNameofStudent;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
