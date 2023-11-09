package com.damjan.librarymanagementsystem.service;

import com.damjan.librarymanagementsystem.model.Author;
import com.damjan.librarymanagementsystem.model.Book;
import com.damjan.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author authorByFirstName(String first_name) {
        return authorRepository.findAuthorByFirst_name(first_name);
    }

    public Author findBySearchInput(String searchInput) {
        String[] words = searchInput.split(" ");

        if (words.length == 2) {
            // Two words: First name and last name
            return authorRepository.findByFirstNameAndLastName(words[0], words[1]);
        } else if (words.length == 3) {
            // Three words: First name, middle name, and last name
            return authorRepository.findByFirstNameAndMiddleNameAndLastName(words[0], words[1], words[2]);
        } else {
            // Handle other cases if needed
            return null;
        }
    }

    public List<Book> findBookbyAuthor(Author author) {
        return authorRepository.findBooksByAuthor(author);
    }
}
