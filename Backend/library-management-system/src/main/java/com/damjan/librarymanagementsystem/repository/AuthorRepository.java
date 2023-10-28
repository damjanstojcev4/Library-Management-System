package com.damjan.librarymanagementsystem.repository;

import com.damjan.librarymanagementsystem.model.Author;
import com.damjan.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author>findAll();
    Author findAuthorByFirst_name(String first_name);
    Author findAuthorByLast_name(String last_name);
    List<Book> findBooksByAuthor(Author author);

}
