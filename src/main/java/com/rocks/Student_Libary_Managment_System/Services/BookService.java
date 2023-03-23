package com.rocks.Student_Libary_Managment_System.Services;

import com.rocks.Student_Libary_Managment_System.DTOs.BookRequestDto;
import com.rocks.Student_Libary_Managment_System.Modles.Author;
import com.rocks.Student_Libary_Managment_System.Modles.Book;
import com.rocks.Student_Libary_Managment_System.Repositorys.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;
    public String addBook(BookRequestDto bookRequestDto){
/* previous soln with out DTOs;

        // basic attribute set by post men

        // I want to get the authorEntity??

        int authorId=book.getAuthorInBook().getId();

        // now i want to fetch the author Entity

        Author author=authorRepository.findById(authorId).get();

        // setting the foregin key att

        book.setAuthorInBook(author);

        // updating list of books
        List<Book>currentBooks=author.getBooksWritten();
        currentBooks.add(book);
        author.setBooksWritten(currentBooks);


        // now the book is to be saved,but the author is to be saved;
        // why author have to update because the author entity has been update so we have to resave it or updatae;
        // we no neet to save book because when author will save the book is automaticly been save due to cascading effect;
        authorRepository.save(author);

 */
        Author author= authorRepository.findById(bookRequestDto.getAuthorId()).get();

        // converter
        Book book=new Book();
        book.setName(bookRequestDto.getName());
        book.setIssued(false);
        book.setPages(bookRequestDto.getPages());
        book.setGenre(bookRequestDto.getGenre());
        // set its forigen key

        book.setAuthorInBook(author);




        // updating list of book of author
        List<Book>currBooks=author.getBooksWritten();
        currBooks.add(book);
        author.setBooksWritten(currBooks);

        // book will automaticly be saved by cascading effect;
        authorRepository.save(author);

        return "book added Sucessfully";




    }
}
