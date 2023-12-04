package dev.patika.LibraryAPI.dto.response.borrow;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowResponse {

    private long id;

    private String borrower_name;

    private LocalDate borrowing_date;

    private LocalDate return_date;

    private int bookId;
}
