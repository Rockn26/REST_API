package dev.patika.LibraryAPI.business.abstracts;

import dev.patika.LibraryAPI.entities.Publisher;
import org.springframework.data.domain.Page;

public interface IPublisherService {
    Publisher save(Publisher publisher);
    Publisher get(int id);

    Page<Publisher> cursor(int page, int pageSize);

    Publisher update(Publisher publisher);

    boolean delete(int id);

}
