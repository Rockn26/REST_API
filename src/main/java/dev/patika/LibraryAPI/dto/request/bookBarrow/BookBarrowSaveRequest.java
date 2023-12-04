package dev.patika.LibraryAPI.dto.request.bookBarrow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBarrowSaveRequest {

    private String borrower_name;

    @DateTimeFormat
    private LocalDate borrowing_date;
    @DateTimeFormat
    private LocalDate return_date;

    private int bookId;
}
