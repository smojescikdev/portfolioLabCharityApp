package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        categoryRepository = categoryRepository;
        this.categoryRepository = categoryRepository;
    }

    //Get all Institutions from database
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


    public List<Category> findAllById(List<Integer> categoryIds) {
        return categoryRepository.findAllById(categoryIds);
    }
}
