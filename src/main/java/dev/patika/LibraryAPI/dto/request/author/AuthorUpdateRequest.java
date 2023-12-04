package dev.patika.LibraryAPI.dto.request.author;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateRequest {

    @Positive(message = "id negatif olamaz!")
    private int id;
    @NotNull
    private String name;
    @DateTimeFormat
    private LocalDate birthDate;
    private String country;
}
