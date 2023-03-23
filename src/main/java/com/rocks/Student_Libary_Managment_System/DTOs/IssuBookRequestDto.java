package com.rocks.Student_Libary_Managment_System.DTOs;

public class IssuBookRequestDto {

    private int bookId;
    private int cardId;

    public IssuBookRequestDto() {
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
