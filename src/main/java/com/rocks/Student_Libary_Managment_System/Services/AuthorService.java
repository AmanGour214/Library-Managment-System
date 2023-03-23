package com.rocks.Student_Libary_Managment_System.Services;

import com.rocks.Student_Libary_Managment_System.DTOs.AuthorEntryDto;
import com.rocks.Student_Libary_Managment_System.DTOs.AuthorResponseDto;
import com.rocks.Student_Libary_Managment_System.DTOs.BookRequestDto;
import com.rocks.Student_Libary_Managment_System.DTOs.BookResponseDto;
import com.rocks.Student_Libary_Managment_System.Modles.Author;
import com.rocks.Student_Libary_Managment_System.Modles.Book;
import com.rocks.Student_Libary_Managment_System.Repositorys.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public String creatAuthor(AuthorEntryDto authorEntryDto){

        // previous code with out dto
        //authorRepository.save(author);

        // now we are using DTOS we need to dtos to entityes because repository
        //only play with Entity level
        Author author=new Author();
        // we are setting its Attribute so that we can save correct values in bd;
        author.setName(authorEntryDto.getName());
        author.setCountry(authorEntryDto.getCountry());
        author.setRating(authorEntryDto.getRating());

        // save author;
        authorRepository.save(author);
        return "Author added sucessfully";
    }

    public AuthorResponseDto  getAuthor(Integer authorId){

        Author author= authorRepository.findById(authorId).get();

        AuthorResponseDto authorResponseDto =new AuthorResponseDto();
        // setting its attribute;

        // we hava to convert List<Book>--> List<BookRequestDto>;

        List<Book>booksList=author.getBooksWritten();

        List<BookResponseDto>bookResponseDtoList=new ArrayList<>();


        // for Eack book we have to convert it to BookRequestsDto

        for(Book book :booksList){
            BookResponseDto bookResponseDto=new BookResponseDto();
            bookResponseDto.setName(book.getName());
            bookResponseDto.setGenre(book.getGenre());
            bookResponseDto.setPages(book.getPages());
            bookResponseDtoList.add(bookResponseDto);

        }
        authorResponseDto.setName(author.getName());
        authorResponseDto.setCountry(author.getCountry());
        authorResponseDto.setRating(author.getRating());
        authorResponseDto.setNoOfBookWritten(bookResponseDtoList);

        return authorResponseDto;

    }


}
