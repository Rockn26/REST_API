package dev.patika.LibraryAPI.api;

import dev.patika.LibraryAPI.business.abstracts.IBookService;
import dev.patika.LibraryAPI.business.abstracts.ICategoryService;
import dev.patika.LibraryAPI.core.config.modelMapper.IModelMapperService;
import dev.patika.LibraryAPI.core.result.Result;
import dev.patika.LibraryAPI.core.result.ResultData;
import dev.patika.LibraryAPI.core.utilies.ResultHelper;
import dev.patika.LibraryAPI.dto.request.book.BookSaveRequest;
import dev.patika.LibraryAPI.dto.request.bookBarrow.BookBarrowUpdateRequest;
import dev.patika.LibraryAPI.dto.request.category.CategorySaveRequest;
import dev.patika.LibraryAPI.dto.request.category.CategoryUpdateRequest;
import dev.patika.LibraryAPI.dto.response.CursorResponse;
import dev.patika.LibraryAPI.dto.response.author.AuthorResponse;
import dev.patika.LibraryAPI.dto.response.book.BookResponse;
import dev.patika.LibraryAPI.dto.response.borrow.BookBorrowResponse;
import dev.patika.LibraryAPI.dto.response.category.CategoryResponse;
import dev.patika.LibraryAPI.entities.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final ICategoryService categoryService;
    private final IModelMapperService modelMapper;
    private final IBookService bookService;


    public CategoryController(ICategoryService categoryService, IModelMapperService modelMapper, IBookService bookService) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
        this.bookService = bookService;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)   // publisheresponse veriyoruz kullanıcadan request olarak almak istiyoruz
    public ResultData<CategoryResponse> save(@Valid @RequestBody CategorySaveRequest categorySaveRequest) {
        Category saveCategory = this.modelMapper.forRequest().map(categorySaveRequest, Category.class);

        this.categoryService.save(saveCategory);

        return ResultHelper.created(this.modelMapper.forResponse().map(saveCategory, CategoryResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> get(@PathVariable("id") int id){
        Category category = this.categoryService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(category, CategoryResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CategoryResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        Page<Category> categoryPage = this.categoryService.cursor(page,pageSize);
        Page<CategoryResponse> categoryResponsePage = categoryPage
                .map(category -> this.modelMapper.forResponse().map(category, CategoryResponse.class));
        return ResultHelper.curse(categoryResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        Category updateCategory = this.modelMapper.forRequest().map(categoryUpdateRequest, Category.class);
        this.categoryService.update(updateCategory);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateCategory, CategoryResponse.class));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.categoryService.delete(id);
        return ResultHelper.ok();
    }




}
