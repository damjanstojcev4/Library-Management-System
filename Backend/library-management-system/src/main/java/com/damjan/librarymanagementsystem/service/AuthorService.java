package com.damjan.librarymanagementsystem.service;

import com.damjan.librarymanagementsystem.exceptions.AuthorNotFoundException;
import com.damjan.librarymanagementsystem.exceptions.BookNotFoundException;
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

    /**
     * Retrieves a list of all authors in the database.
     *
     * @return List of authors
     */
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    /**
     * Retrieves an author based on the first name.
     *
     * @param first_name The first name of the author
     * @return The author with the specified first name
     * @throws AuthorNotFoundException If no author is found with the given first name
     */
    public Author authorByFirstName(String first_name) {
        Author author = authorRepository.findAuthorByFirst_name(first_name);

        if (author == null) {
            throw new AuthorNotFoundException("Author with first name " + first_name + " not found");
        }

        return author;
    }

    /**
     * Searches for an author based on a search input that can include first name,
     * last name, and middle name.
     *
     * @param searchInput The input for author search
     * @return The matching author
     * @throws AuthorNotFoundException If no author is found based on the search input
     */
    public Author findBySearchInput(String searchInput) {
        String[] words = searchInput.split(" ");

        if (words.length == 2) {
            // Two words: First name and last name
            return authorRepository.findByFirstNameAndLastName(words[0], words[1]);
        } else if (words.length == 3) {
            // Three words: First name, middle name, and last name
            return authorRepository.findByFirstNameAndMiddleNameAndLastName(words[0], words[1], words[2]);
        } else {
            throw new AuthorNotFoundException("Author with " + words + " not found");
        }
    }

    /**
     * Retrieves a list of books associated with a specific author.
     *
     * @param author The author for whom books are retrieved
     * @return List of books by the author
     * @throws BookNotFoundException If no books are found for the author
     */
    public List<Book> findBookbyAuthor(Author author) {
        List<Book> books = authorRepository.findBooksByAuthor(author);

        if (books.isEmpty()) {
            throw new BookNotFoundException("No books found for author: " + author.getFirst_name() + " " + author.getLast_name());
        }

        return books;
    }
}

