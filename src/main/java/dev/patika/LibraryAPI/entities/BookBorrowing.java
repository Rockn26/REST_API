package dev.patika.LibraryAPI.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "borrowers")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookBorrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrowers_id", columnDefinition = "serial")
    private long id;
    @Column(name = "borrower_name", nullable = false)
    private String borrower_name;
    @Temporal(TemporalType.DATE)
    @Column(name = "borroing_date", nullable = false)
    private LocalDate borrowing_date;

    @Temporal(TemporalType.DATE)
    @Column(name = "return_date", nullable = false)
    private LocalDate return_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_book_id", referencedColumnName = "book_id")
    private Book book;


}
