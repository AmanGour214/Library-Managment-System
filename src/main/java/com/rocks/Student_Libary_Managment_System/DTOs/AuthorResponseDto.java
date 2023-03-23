package com.rocks.Student_Libary_Managment_System.DTOs;

import java.util.List;

public class AuthorResponseDto {

    private String name;

    private String country;

    private double rating;

    private List<BookResponseDto>noOfBookWritten;

    public AuthorResponseDto() {


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<BookResponseDto> getNoOfBookWritten() {
        return noOfBookWritten;
    }

    public void setNoOfBookWritten(List<BookResponseDto> noOfBookWritten) {
        this.noOfBookWritten = noOfBookWritten;
    }
}
