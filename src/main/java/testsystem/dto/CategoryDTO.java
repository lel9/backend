package testsystem.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CategoryDTO {

    @NotNull(message = "Идентификатор категории должен быть задан")
    @NotEmpty(message = "Идентификатор категории не должен быть пуст")
    @JsonView({CategoryView.EDIT.class, CategoryView.DELETE.class, CategoryView.LIST.class})
    private String id;

    @NotNull(message = "Название категории должно быть задано")
    @NotEmpty(message = "Название категории не должно быть пусто")
    @JsonView({CategoryView.ADD.class, CategoryView.EDIT.class, CategoryView.LIST.class})
    private String name;

    @JsonView({CategoryView.LIST.class})
    private int count;
}
