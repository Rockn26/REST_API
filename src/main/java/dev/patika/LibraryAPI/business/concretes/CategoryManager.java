package dev.patika.LibraryAPI.business.concretes;

import dev.patika.LibraryAPI.business.abstracts.ICategoryService;
import dev.patika.LibraryAPI.dao.CategoryRepo;
import dev.patika.LibraryAPI.entities.Category;
import dev.patika.LibraryAPI.entities.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager implements ICategoryService {

    @Autowired
    private final CategoryRepo categoryRepo;

    public CategoryManager(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }


    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category get(int id) {
        return categoryRepo.findById(id).orElseThrow();
    }

    @Override
    public Page<Category> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.categoryRepo.findAll(pageable);
    }

    @Override
    public Category update(Category category) {
        this.get((int) category.getId());
        return this.categoryRepo.save(category);

    }

    @Override
    public boolean delete(int id) {
        Category category = this.get(id);
        this.categoryRepo.delete(category);
        return true;
    }
}
