package dev.patika.LibraryAPI.dto.request.publisher;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherUpdateRequest {
    @Positive(message = "ID değeri pozitif sayı olmak zorunda")
    private int id;
    @NotNull(message = "Bu alan boş olamaz")
    private String name;
    private int establishmentYear;
    private String address;
}
