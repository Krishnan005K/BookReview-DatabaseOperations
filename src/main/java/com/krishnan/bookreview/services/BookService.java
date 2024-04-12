package com.krishnan.bookreview.services;

import com.krishnan.bookreview.model.Book;
import com.krishnan.bookreview.repository.BookRepository;
import com.krishnan.bookreview.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    @Autowired
    private ReviewRepository reviewRepository;

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

    public Page<Book> getAllBooksPaged(int page, int size, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
        return bookRepository.findAll(pageRequest);
    }

    public List<Book> getAllBooksSorted(String sortBy) {
        return bookRepository.findAll(Sort.by(sortBy));
    }

    public List<Book> findBooksByRatingGreaterThan(float rating) {
        return reviewRepository.findBooksByRatingGreaterThan(rating);
    }

    public List<Book> findBooksByYear(String year) {
        return bookRepository.findBooksByYear(year);
    }
}

