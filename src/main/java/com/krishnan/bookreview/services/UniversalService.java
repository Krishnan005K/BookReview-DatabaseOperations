package com.krishnan.bookreview.services;

import com.krishnan.bookreview.model.Book;
import com.krishnan.bookreview.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UniversalService {

    @Autowired
    private BookRepository bookRepository;


    public List<Book> getAllBooksSorted(String sortBy) {
        return bookRepository.findAll(Sort.by(sortBy));
    }

    public Page<Book> getAllBooksPaged(int page, int size, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
        return bookRepository.findAll(pageRequest);
    }

}
