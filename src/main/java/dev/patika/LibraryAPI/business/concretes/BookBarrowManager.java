package dev.patika.LibraryAPI.business.concretes;

import dev.patika.LibraryAPI.business.abstracts.IBookBarrowService;
import dev.patika.LibraryAPI.core.exception.NotFoundException;
import dev.patika.LibraryAPI.core.utilies.Msg;
import dev.patika.LibraryAPI.dao.BookBorrowingRepo;
import dev.patika.LibraryAPI.entities.BookBorrowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookBarrowManager implements IBookBarrowService {

    @Autowired
    private final BookBorrowingRepo borrowingRepo;

    public BookBarrowManager(BookBorrowingRepo borrowingRepo) {
        this.borrowingRepo = borrowingRepo;
    }


    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {
        return this.borrowingRepo.save(bookBorrowing);
    }

    @Override
    public BookBorrowing get(int id) {
        return this.borrowingRepo.findById(id).orElseThrow( () -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<BookBorrowing> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.borrowingRepo.findAll(pageable);
    }

    @Override
    public BookBorrowing update(BookBorrowing bookBorrowing) {
        this.get((int) bookBorrowing.getId());
        return this.borrowingRepo.save(bookBorrowing);
    }

    @Override
    public boolean delete(int id) {
        BookBorrowing borrowing = this.get(id);
        this.borrowingRepo.save(borrowing);
        return true;
    }
}
