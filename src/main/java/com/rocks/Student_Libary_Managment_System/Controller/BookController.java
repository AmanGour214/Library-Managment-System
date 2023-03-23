package com.rocks.Student_Libary_Managment_System.Controller;

import com.rocks.Student_Libary_Managment_System.DTOs.BookRequestDto;
import com.rocks.Student_Libary_Managment_System.Modles.Book;
import com.rocks.Student_Libary_Managment_System.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody BookRequestDto bookRequestDto){
        return bookService.addBook(bookRequestDto);
    }

}
