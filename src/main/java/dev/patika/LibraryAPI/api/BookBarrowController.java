package dev.patika.LibraryAPI.api;

import dev.patika.LibraryAPI.business.abstracts.IBookBarrowService;
import dev.patika.LibraryAPI.business.abstracts.IBookService;
import dev.patika.LibraryAPI.core.config.modelMapper.IModelMapperService;
import dev.patika.LibraryAPI.core.result.Result;
import dev.patika.LibraryAPI.core.result.ResultData;
import dev.patika.LibraryAPI.core.utilies.ResultHelper;
import dev.patika.LibraryAPI.dto.request.book.BookSaveRequest;
import dev.patika.LibraryAPI.dto.request.bookBarrow.BookBarrowSaveRequest;
import dev.patika.LibraryAPI.dto.request.bookBarrow.BookBarrowUpdateRequest;
import dev.patika.LibraryAPI.dto.request.publisher.PublisherUpdateRequest;
import dev.patika.LibraryAPI.dto.response.CursorResponse;
import dev.patika.LibraryAPI.dto.response.author.AuthorResponse;
import dev.patika.LibraryAPI.dto.response.book.BookResponse;
import dev.patika.LibraryAPI.dto.response.borrow.BookBorrowResponse;
import dev.patika.LibraryAPI.dto.response.publisher.PublisherResponse;
import dev.patika.LibraryAPI.entities.Author;
import dev.patika.LibraryAPI.entities.Book;
import dev.patika.LibraryAPI.entities.BookBorrowing;
import dev.patika.LibraryAPI.entities.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/borrowers")
public class BookBarrowController {

    private final IBookBarrowService bookBarrowService;
    private final IModelMapperService modelMapper;
    private final IBookService bookService;

    public BookBarrowController(IBookBarrowService bookBarrowService, IModelMapperService modelMapper, IBookService bookService) {
        this.bookBarrowService = bookBarrowService;
        this.modelMapper = modelMapper;
        this.bookService = bookService;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)   // publisheresponse veriyoruz kullanıcadan request olarak almak istiyoruz
    public ResultData<BookBorrowResponse> save(@Valid @RequestBody BookBarrowSaveRequest barrowSaveRequest) {
        BookBorrowing saveBookBarrow = this.modelMapper.forRequest().map(barrowSaveRequest, BookBorrowing.class);
        Book book = this.bookService.get(barrowSaveRequest.getBookId());
        saveBookBarrow.setBook(book);

        this.bookBarrowService.save(saveBookBarrow);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBookBarrow, BookBorrowResponse.class));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowResponse> get(@PathVariable("id") int id){
        BookBorrowing borrowing = this.bookBarrowService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(borrowing, BookBorrowResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookBorrowResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        Page<BookBorrowing> barrowerPage = this.bookBarrowService.cursor(page,pageSize);
        Page<BookBorrowResponse> borrowerResponsePage = barrowerPage
                .map(borrowing -> this.modelMapper.forResponse().map(borrowing, BookBorrowResponse.class));
        return ResultHelper.curse(borrowerResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowResponse> update(@Valid @RequestBody BookBarrowUpdateRequest bookBarrowUpdateRequest) {
        BookBorrowing updateBorrow = this.modelMapper.forRequest().map(bookBarrowUpdateRequest, BookBorrowing.class);
        this.bookBarrowService.update(updateBorrow);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateBorrow, BookBorrowResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.bookBarrowService.delete(id);
        return ResultHelper.ok();
    }


}
