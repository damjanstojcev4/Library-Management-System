package com.damjan.librarymanagementsystem.repository;

import com.damjan.librarymanagementsystem.model.Book;
import com.damjan.librarymanagementsystem.model.Transaction;
import com.damjan.librarymanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    //This is useful for showing a user's transaction history.
    List<Transaction> findTransactionsByUser(User user);

    // This can be useful for tracking late returns and calculating late fees
    @Query("SELECT t FROM Transaction t WHERE t.status = 'Overdue' AND t.checkIn > t.dueDate")
    List<Transaction> findOverdueTransactions();


    // find all transactions related to a specific book
    List<Transaction> findTransactionsByBook(Book book);

    // Active Transactions where book is currently checked out
    @Query("SELECT t FROM Transaction t WHERE t.status = 'CHECKED_OUT' AND t.dueDate >= CURRENT_TIMESTAMP")
    List<Transaction> findActiveTransactions();

    //  find all transactions associated with a specific user and a SPECIFIC BOOK
    List<Transaction> findTransactionsByUserAndBook(User user, Book book);

    // calculate total fees
    @Query("SELECT SUM(t.lateFee) FROM Transaction t WHERE t.user = :user AND t.status = 'OVERDUE'")
    Double calculateTotalFinesForUser(@Param("user") User user);

}
