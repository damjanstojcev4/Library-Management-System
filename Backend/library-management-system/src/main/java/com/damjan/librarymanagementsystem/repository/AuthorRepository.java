package com.damjan.librarymanagementsystem.repository;

import com.damjan.librarymanagementsystem.model.Author;
import com.damjan.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author>findAll();
    Author findAuthorByFirst_name(String first_name);
    Author findAuthorByLast_name(String last_name);

    // find Author by Full Name WITHOUT MIDDLE NAME
    Author findByFirstNameAndLastName(String firstName, String lastName);

    // find Author by Full name WITH MIDDLE NAME
    Author findByFirstNameAndMiddleNameAndLastName(String firstName, String middleName, String lastName);

    // Searches for an author by a full name, considering the middle name if present
    @Query("SELECT a FROM Author a WHERE " +
            "CONCAT(a.first_name, ' ', COALESCE(a.middle_name, ''), ' ', a.last_name) = :fullName")
    Author findByFullName(@Param("fullName") String fullName);

    // retrieves a list of books associated with a specific author
    List<Book> findBooksByAuthor(Author author);

}
