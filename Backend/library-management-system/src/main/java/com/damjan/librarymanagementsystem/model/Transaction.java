package com.damjan.librarymanagementsystem.model;

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

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    @PrePersist
    protected void onCheckIn() {
        this.checkIn = LocalDateTime.now();
    }
}
