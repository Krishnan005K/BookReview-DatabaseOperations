package com.krishnan.bookreview.controller;

import com.krishnan.bookreview.model.Book;
import com.krishnan.bookreview.model.Review;
import com.krishnan.bookreview.model.User;
import com.krishnan.bookreview.services.UniversalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UniversalController {

    @Autowired
    private UniversalService universalService;

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = universalService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = universalService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = universalService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        boolean deleted = universalService.deleteUserById(id);
        if (deleted) {
            return ResponseEntity.ok("Deleted User successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = universalService.updateUser(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = universalService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = universalService.getBookById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = universalService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id) {
        boolean deleted = universalService.deleteBookById(id);
        if (deleted) {
            return ResponseEntity.ok("Deleted Book successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = universalService.updateBook(id, book);
        if (updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addReview")
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        User user = universalService.getUserById(review.getUserId());
        Book book = universalService.getBookById(review.getBookId());

        if (user == null || book == null) {
            return ResponseEntity.notFound().build();
        }

        review.setUser(user);
        review.setBook(book);

        Review savedReview = universalService.addReview(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    @GetMapping("/getReviewById/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Review review = universalService.getReviewById(id);
        if (review != null) {
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllReviews")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = universalService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/deleteReviewById/{id}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long id) {
        boolean deleted = universalService.deleteReviewById(id);
        if (deleted) {
            return ResponseEntity.ok("Deleted Review successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateReview/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review) {
        Review updatedReview = universalService.updateReview(id, review);
        if (updatedReview != null) {
            return ResponseEntity.ok(updatedReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // @Query

    @GetMapping("/books/rating/{rating}")
    public ResponseEntity<List<Book>> getBooksByRatingGreaterThan(@PathVariable float rating) {
        List<Book> books = universalService.findBooksByRatingGreaterThan(rating);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/year/{year}")
    public ResponseEntity<List<Book>> getBooksByYear(@PathVariable String year) {
        List<Book> books = universalService.findBooksByYear(year);
        return ResponseEntity.ok(books);
    }




    //pagination and sorting
    @GetMapping("/books")
    public ResponseEntity<Page<Book>> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "bookId") String sortBy) {

        Page<Book> bookPage = universalService.getAllBooksPaged(page, size, sortBy);

        return ResponseEntity.ok(bookPage);
    }

    @GetMapping("/sortedBooks")
    public ResponseEntity<List<Book>> getSortedBooks(@RequestParam String sortBy) {
        List<Book> sortedBooks = universalService.getAllBooksSorted(sortBy);
        return ResponseEntity.ok(sortedBooks);
    }
}
