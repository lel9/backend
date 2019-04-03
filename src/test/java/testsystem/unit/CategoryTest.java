package testsystem.unit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import testsystem.domain.Category;
import testsystem.dto.CategoryDTO;
import testsystem.exception.CategoryAlreadyExistsException;
import testsystem.exception.NoSuchCategoryException;
import testsystem.repository.CategoryRepository;
import testsystem.service.CategoryServiceImpl;

import java.util.ArrayList;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CategoryTest {

    @Mock
    private CategoryRepository repositoryMock;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category1;
    private Category category2;

    @Before
    public void init() {
        category1 = new Category("cat1", new ArrayList<>());
        category2 = new Category("cat2", new ArrayList<>());
    }

    @Test
    public void addCategoryIfNotExists() {
        CategoryDTO categoryDTO = new CategoryDTO(null, "cat1", 0);
        Mockito.when(repositoryMock.findByName("cat1")).thenReturn(null);
        Mockito.when(repositoryMock.save(Mockito.argThat(argument -> {
            Assert.assertEquals(category1.getName(), argument.getName());
            return true;
        }))).thenReturn(category1);

        Category added = categoryService.addNewCategory(categoryDTO);

        Assert.assertEquals(category1, added);
        Mockito.verify(repositoryMock, Mockito.times(1)).findByName(category1.getName());
        Mockito.verify(repositoryMock, Mockito.times(1)).save(Mockito.any(Category.class));
        Mockito.verifyNoMoreInteractions(repositoryMock);
    }

    @Test(expected = CategoryAlreadyExistsException.class)
    public void addCategoryIfExists() {
        CategoryDTO categoryDTO = new CategoryDTO(null, "cat1", 0);
        Mockito.when(repositoryMock.findByName("cat1")).thenReturn(category1);

        Category added = categoryService.addNewCategory(categoryDTO);
        Mockito.verifyNoMoreInteractions(repositoryMock);
    }

    @Test
    public void deleteCategoryIfExists() {
        CategoryDTO categoryDTO = new CategoryDTO(category1.getId().toString(), null, 0);
        Mockito.when(repositoryMock.findById(category1.getId())).thenReturn(Optional.of(category1));

        categoryService.deleteCategory(categoryDTO);

        Mockito.verify(repositoryMock, Mockito.times(1)).findById(category1.getId());
        Mockito.verify(repositoryMock, Mockito.times(1)).delete(Mockito.argThat(argument -> {
            Assert.assertEquals(category1.getName(), argument.getName());
            return true;
        }));

        Mockito.verifyNoMoreInteractions(repositoryMock);
    }

    @Test(expected = NoSuchCategoryException.class)
    public void deleteCategoryIfNotExists() {
        CategoryDTO categoryDTO = new CategoryDTO(category1.getId().toString(), null, 0);
        Mockito.when(repositoryMock.findById(category1.getId())).thenReturn(Optional.empty());

        categoryService.deleteCategory(categoryDTO);

        Mockito.verify(repositoryMock, Mockito.times(1)).findById(category1.getId());
        Mockito.verifyNoMoreInteractions(repositoryMock);
    }

    @Test(expected = NoSuchCategoryException.class)
    public void deleteCategoryIfNotExists2() {
        CategoryDTO categoryDTO = new CategoryDTO("123", null, 0);

        categoryService.deleteCategory(categoryDTO);

        Mockito.verifyNoMoreInteractions(repositoryMock);
    }

    @Test
    public void editCategorySuccess() {
        CategoryDTO categoryDTO = new CategoryDTO(category1.getId().toString(), "cat2", 0);
        Mockito.when(repositoryMock.findById(category1.getId())).thenReturn(Optional.of(category1));
        Mockito.when(repositoryMock.findByName(category2.getName())).thenReturn(null);
        Mockito.when(repositoryMock.save(Mockito.argThat(argument -> {
            Assert.assertEquals(category2.getName(), argument.getName());
            return true;
        }))).thenReturn(category2);

        Category edited = categoryService.editCategory(categoryDTO);

        Assert.assertEquals(category2, edited);

        Mockito.verify(repositoryMock, Mockito.times(1)).findById(category1.getId());
        Mockito.verify(repositoryMock, Mockito.times(1)).findByName(category2.getName());
        Mockito.verify(repositoryMock, Mockito.times(1)).save(Mockito.argThat(argument -> {
            Assert.assertEquals(category2.getName(), argument.getName());
            return true;
        }));
        Mockito.verifyNoMoreInteractions(repositoryMock);
    }

    @Test
    public void editCategoryNameExistsSuccess() {
        CategoryDTO categoryDTO = new CategoryDTO(category1.getId().toString(), "cat1", 0);
        Mockito.when(repositoryMock.findById(category1.getId())).thenReturn(Optional.of(category1));
        Mockito.when(repositoryMock.save(Mockito.argThat(argument -> {
            Assert.assertEquals(category1.getName(), argument.getName());
            return true;
        }))).thenReturn(category1);

        Category edited = categoryService.editCategory(categoryDTO);

        Assert.assertEquals(category1, edited);

        Mockito.verify(repositoryMock, Mockito.times(1)).findById(category1.getId());
        Mockito.verify(repositoryMock, Mockito.times(1)).save(Mockito.argThat(argument -> {
            Assert.assertEquals(category1.getName(), argument.getName());
            return true;
        }));
        Mockito.verifyNoMoreInteractions(repositoryMock);
    }

    @Test(expected = CategoryAlreadyExistsException.class)
    public void editCategoryNameExistsFail() {
        CategoryDTO categoryDTO = new CategoryDTO(category1.getId().toString(), "cat2", 0);
        Mockito.when(repositoryMock.findById(category1.getId())).thenReturn(Optional.of(category1));
        Mockito.when(repositoryMock.findByName(category2.getName())).thenReturn(category2);

        categoryService.editCategory(categoryDTO);

        Mockito.verify(repositoryMock, Mockito.times(1)).findById(category1.getId());
        Mockito.verify(repositoryMock, Mockito.times(1)).findByName(category2.getName());
        Mockito.verifyNoMoreInteractions(repositoryMock);
    }

    @Test(expected = NoSuchCategoryException.class)
    public void editCategoryNotExists() {
        CategoryDTO categoryDTO = new CategoryDTO(category1.getId().toString(), "cat2", 0);
        Mockito.when(repositoryMock.findById(category1.getId())).thenReturn(Optional.empty());

        categoryService.editCategory(categoryDTO);

        Mockito.verify(repositoryMock, Mockito.times(1)).findById(category1.getId());
        Mockito.verifyNoMoreInteractions(repositoryMock);
    }

}
