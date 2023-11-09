package com.damjan.librarymanagementsystem.service;

import com.damjan.librarymanagementsystem.enums.TransactionStatus;
import com.damjan.librarymanagementsystem.model.Book;
import com.damjan.librarymanagementsystem.model.Transaction;
import com.damjan.librarymanagementsystem.model.User;
import com.damjan.librarymanagementsystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public int calculateLateFees(Transaction transaction) {
        LocalDateTime checkIn = transaction.getCheckIn();
        LocalDateTime dueDate = transaction.getDueDate();

        if (checkIn.isAfter(dueDate)) {
            // Calculate the difference in days
            int daysLate = (int) ChronoUnit.DAYS.between(dueDate, checkIn);
            // Calculate the late fee (5 denari per day)
            int lateFee = daysLate * 5;
            return lateFee;
        } else {
            return 0; // no late fees
        }
    }

    public List<Transaction> findTransactionsByUser(User user) {
        return transactionRepository.findTransactionsByUser(user);
    }

    public List<Transaction> findOverdueTransactions() {
        return transactionRepository.findOverdueTransactions();
    }

    public List<Transaction> findActiveTransactions() {
        return transactionRepository.findActiveTransactions();
    }

    public List<Transaction> findTransactionsByBook(Book book) {
        return transactionRepository.findTransactionsByBook(book);
    }

    public List<Transaction> findTransactionsByUserAndBook(User user, Book book) {
        return transactionRepository.findTransactionsByUserAndBook(user, book);
    }

    public Double calculateTotalFinesForUser(User user) {
        return transactionRepository.calculateTotalFinesForUser(user);
    }

    public void checkOutBook(User user, Book book) {
        // Check if the book is available
        if (bookService.isBookAvailable(book)) {
            Transaction transaction = new Transaction();
            transaction.setUser(user);
            transaction.setBook(book);
            transaction.setStatus(TransactionStatus.CHECKED_OUT);

            // Set the due date (e.g., 14 days from now)
            LocalDateTime dueDate = LocalDateTime.now().plusDays(14);
            transaction.setDueDate(dueDate);

            // Save the transaction
            transactionRepository.save(transaction);
        } else {
            // Handle the case when the book is not available
        }
    }
    public void checkInBook(Transaction transaction) {
        // Calculate late fees
        int lateFee = (int) calculateLateFees(transaction);
        transaction.setLateFee(lateFee);

        // Update the transaction status to "RETURNED"
        transaction.setStatus(TransactionStatus.RETURNED);

        // Save the transaction
        transactionRepository.save(transaction);
    }

    public void reserveBook(User user, Book book) {
        // Implement book reservation logic, update transaction status.
    }

    public void cancelReservation(Transaction transaction) {
        // Implement reservation cancellation logic, update transaction status.
    }

}

