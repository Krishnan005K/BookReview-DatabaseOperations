package com.krishnan.bookreview.services;

import com.krishnan.bookreview.model.Book;
import com.krishnan.bookreview.model.Review;
import com.krishnan.bookreview.model.User;
import com.krishnan.bookreview.repository.BookRepository;
import com.krishnan.bookreview.repository.ReviewRepository;
import com.krishnan.bookreview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversalService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Book> getAllBooksSorted(String sortBy) {
        return bookRepository.findAll(Sort.by(sortBy));
    }

    public Page<Book> getAllBooksPaged(int page, int size, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
        return bookRepository.findAll(pageRequest);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setMobile(updatedUser.getMobile());
            return userRepository.save(user);
        }
        return null;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public boolean deleteBookById(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setBookName(updatedBook.getBookName());
            book.setGenre(updatedBook.getGenre());
            book.setLaunchYear(updatedBook.getLaunchYear());
            book.setAuthor(updatedBook.getAuthor());
            return bookRepository.save(book);
        }
        return null;
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review getReviewById(Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        return optionalReview.orElse(null);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public boolean deleteReviewById(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Review updateReview(Long id, Review updatedReview) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setReviewData(updatedReview.getReviewData());
            review.setReviewDate(updatedReview.getReviewDate());
            return reviewRepository.save(review);
        }
        return null;
    }


    //@Query annotaion
    public List<Book> findBooksByRatingGreaterThan(float rating) {
        return reviewRepository.findBooksByRatingGreaterThan(rating);
    }
    public List<Book> findBooksByYear(String year) {
        return bookRepository.findBooksByYear(year);
    }


    
}
