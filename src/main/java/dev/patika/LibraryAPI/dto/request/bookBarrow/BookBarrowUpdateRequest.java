package dev.patika.LibraryAPI.dto.request.bookBarrow;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBarrowUpdateRequest {

    private String name;

    private int publicationYear;

    private int stock;

    private int authorId;

    private int publisherId;
}
