package dev.patika.LibraryAPI.dto.response.book;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private long id;

    private String name;

    private int publicationYear;

    private int stock;

    private int authorId;

    private int publisherId;
}
