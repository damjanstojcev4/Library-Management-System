package com.damjan.librarymanagementsystem.repository;

import com.damjan.librarymanagementsystem.model.Author;
import com.damjan.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAll();
    Book findByTitle();
}
