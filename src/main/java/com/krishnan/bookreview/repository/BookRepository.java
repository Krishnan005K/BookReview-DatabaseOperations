package com.krishnan.bookreview.repository;

import com.krishnan.bookreview.model.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    
    @Query("SELECT b FROM Book b WHERE b.launchYear = :year")
    List<Book> findBooksByYear(@Param("year") String year);

}
