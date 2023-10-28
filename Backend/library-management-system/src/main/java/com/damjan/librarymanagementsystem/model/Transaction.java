package com.damjan.librarymanagementsystem.model;

import com.damjan.librarymanagementsystem.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime checkIn;
    private LocalDateTime dueDate;
    private double lateFee;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    @PrePersist
    protected void onCheckIn() {
        this.checkIn = LocalDateTime.now();
    }
}
