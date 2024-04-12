package com.krishnan.bookreview.controller;

import com.krishnan.bookreview.model.Book;


import com.krishnan.bookreview.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id) {
        boolean deleted = bookService.deleteBookById(id);
        if (deleted) {
            return ResponseEntity.ok("Deleted Book successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        if (updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

       // @Query

       @GetMapping("/books/rating/{rating}")
       public ResponseEntity<List<Book>> getBooksByRatingGreaterThan(@PathVariable float rating) {
           List<Book> books = bookService.findBooksByRatingGreaterThan(rating);
           return ResponseEntity.ok(books);
       }
   
       @GetMapping("/books/year/{year}")
       public ResponseEntity<List<Book>> getBooksByYear(@PathVariable String year) {
           List<Book> books = bookService.findBooksByYear(year);
           return ResponseEntity.ok(books);
       }
}
