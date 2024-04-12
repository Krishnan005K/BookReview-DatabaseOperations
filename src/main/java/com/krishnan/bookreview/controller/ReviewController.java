package com.krishnan.bookreview.controller;

import com.krishnan.bookreview.model.Book;
import com.krishnan.bookreview.model.Review;
import com.krishnan.bookreview.model.User;
import com.krishnan.bookreview.services.BookService;
import com.krishnan.bookreview.services.ReviewService;
import com.krishnan.bookreview.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookService bookService;

    @Autowired 
    private UserService userService;
    

    @PostMapping("/addReview")
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        User user = userService.getUserById(review.getUserId());
        Book book = bookService.getBookById(review.getBookId());

        if (user == null || book == null) {
            return ResponseEntity.notFound().build();
        }

        review.setUser(user);
        review.setBook(book);

        Review savedReview = reviewService.addReview(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    @GetMapping("/getReviewById/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);
        if (review != null) {
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllReviews")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/deleteReviewById/{id}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long id) {
        boolean deleted = reviewService.deleteReviewById(id);
        if (deleted) {
            return ResponseEntity.ok("Deleted Review successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateReview/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review) {
        Review updatedReview = reviewService.updateReview(id, review);
        if (updatedReview != null) {
            return ResponseEntity.ok(updatedReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
