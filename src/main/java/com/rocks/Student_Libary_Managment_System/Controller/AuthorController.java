package com.rocks.Student_Libary_Managment_System.Controller;

import com.rocks.Student_Libary_Managment_System.DTOs.AuthorEntryDto;
import com.rocks.Student_Libary_Managment_System.DTOs.AuthorResponseDto;
import com.rocks.Student_Libary_Managment_System.Modles.Author;
import com.rocks.Student_Libary_Managment_System.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String creatAuthor(@RequestBody AuthorEntryDto authorEntryDto){

        return authorService.creatAuthor(authorEntryDto);
    }

    @GetMapping("/getAuthor")
    public AuthorResponseDto  getAuthor(@RequestParam("authorId")Integer authorId){
        return authorService.getAuthor(authorId);
    }
}
