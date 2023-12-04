package dev.patika.LibraryAPI.business.concretes;

import dev.patika.LibraryAPI.business.abstracts.IPublisherService;
import dev.patika.LibraryAPI.core.exception.NotFoundException;
import dev.patika.LibraryAPI.core.utilies.Msg;
import dev.patika.LibraryAPI.dao.PublisherRepo;
import dev.patika.LibraryAPI.entities.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PublisherManager implements IPublisherService {

    @Autowired
    private final PublisherRepo publisherRepo;

    public PublisherManager(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public Publisher save(Publisher publisher) {
        return this.publisherRepo.save(publisher);
    }

    @Override
    public Publisher get(int id) {
        return this.publisherRepo.findById(id).orElseThrow( () -> new NotFoundException(Msg.NOT_FOUND)); // Null olabilir o yüzden orElseThrow kullanacğaız
    }

    @Override
    public Page<Publisher> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.publisherRepo.findAll(pageable);
    }

    @Override
    public Publisher update(Publisher publisher) {
        this.get((int) publisher.getId());
        return this.publisherRepo.save(publisher);
    }

    @Override
    public boolean delete(int id) {
        Publisher publisher = this.get(id);
        this.publisherRepo.delete(publisher);
        return true;
    }

}
