package dev.patika.LibraryAPI.business.abstracts;

import dev.patika.LibraryAPI.entities.Author;
import dev.patika.LibraryAPI.entities.BookBorrowing;
import org.springframework.data.domain.Page;

public interface IBookBarrowService {
    BookBorrowing save(BookBorrowing bookBorrowing);
    BookBorrowing get(int id);
    Page<BookBorrowing> cursor(int page, int pageSize);
    BookBorrowing update(BookBorrowing BookBorrowing);
    boolean delete(int id);
}
