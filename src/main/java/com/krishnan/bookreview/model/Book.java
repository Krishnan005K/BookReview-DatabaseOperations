package com.krishnan.bookreview.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="book_data")
public class Book {
    

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int bookId;
    private String bookName;
    private String genre;
    private String launchYear;
    private String author;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews;


}
