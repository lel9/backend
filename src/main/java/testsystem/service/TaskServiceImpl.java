package testsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testsystem.domain.Category;
import testsystem.exception.CategoryAlreadyExistsException;
import testsystem.repository.CategoryRepository;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void addNewCategory(Category category) {
        if (categoryRepository.findByName(category.getName()) != null) {
            throw new CategoryAlreadyExistsException(category.getName());
        }
        categoryRepository.save(category);
    }
}
