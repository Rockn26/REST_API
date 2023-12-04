package dev.patika.LibraryAPI.api;

import dev.patika.LibraryAPI.business.abstracts.IAuthorService;
import dev.patika.LibraryAPI.business.abstracts.IBookService;
import dev.patika.LibraryAPI.business.abstracts.IPublisherService;
import dev.patika.LibraryAPI.core.config.modelMapper.IModelMapperService;
import dev.patika.LibraryAPI.core.result.Result;
import dev.patika.LibraryAPI.core.result.ResultData;
import dev.patika.LibraryAPI.core.utilies.ResultHelper;
import dev.patika.LibraryAPI.dto.request.book.BookSaveRequest;
import dev.patika.LibraryAPI.dto.request.book.BookUpdateRequest;
import dev.patika.LibraryAPI.dto.request.publisher.PublisherUpdateRequest;
import dev.patika.LibraryAPI.dto.response.CursorResponse;
import dev.patika.LibraryAPI.dto.response.author.AuthorResponse;
import dev.patika.LibraryAPI.dto.response.book.BookResponse;
import dev.patika.LibraryAPI.dto.response.publisher.PublisherResponse;
import dev.patika.LibraryAPI.entities.Author;
import dev.patika.LibraryAPI.entities.Book;
import dev.patika.LibraryAPI.entities.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final IBookService bookService;
    private final IModelMapperService modelMapper;
    private final IAuthorService authorService;
    private final IPublisherService publisherService;

    public BookController(IBookService bookService, IModelMapperService modelMapper, IAuthorService authorService, IPublisherService publisherService) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)   // publisheresponse veriyoruz kullanıcadan request olarak almak istiyoruz
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest) {
        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest, Book.class);

        Author author = this.authorService.get(bookSaveRequest.getAuthorId());
        saveBook.setAuthor(author);

        Publisher publisher = this.publisherService.get(bookSaveRequest.getPublisherId());
        saveBook.setPublisher(publisher);

        this.bookService.save(saveBook);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBook, BookResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> get(@PathVariable("id") int id){
        Book book = this.bookService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(book, BookResponse.class));
    }

    @GetMapping("/{id}/authors")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> getAuthor(@PathVariable("id") int id){
        Book book = this.bookService.get(id);

        return ResultHelper.success(this.modelMapper.forResponse().map(book.getAuthor(), AuthorResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        Page<Book> bookPage = this.bookService.cursor(page,pageSize);
        Page<BookResponse> bookResponsePage = bookPage
                .map(book -> this.modelMapper.forResponse().map(book, BookResponse.class));
        return ResultHelper.curse(bookResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)   // publisheresponse veriyoruz kullanıcadan request olarak almak istiyoruz
    public ResultData<PublisherResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest) {
        Book updateBook = this.modelMapper.forRequest().map(bookUpdateRequest, Book.class);
        this.bookService.update(updateBook);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateBook, PublisherResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.bookService.delete(id);
        return ResultHelper.ok();
    }

}
