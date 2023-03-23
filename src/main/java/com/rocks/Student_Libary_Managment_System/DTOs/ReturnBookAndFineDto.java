package com.rocks.Student_Libary_Managment_System.DTOs;

public class ReturnBookAndFineDto {
    private int bookId;
    private int cardId;

    public ReturnBookAndFineDto() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
