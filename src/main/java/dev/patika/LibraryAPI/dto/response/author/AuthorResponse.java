package dev.patika.LibraryAPI.dto.response.author;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {

    private int id;
    private String name;
    private LocalDate birthDate;
    private String country;
}
