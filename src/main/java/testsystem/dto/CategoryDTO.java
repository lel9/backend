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

    @NotNull
    @NotEmpty
    @JsonView({CategoryView.EDIT.class, CategoryView.LIST.class})
    private String id;

    @NotNull
    @NotEmpty
    @JsonView({CategoryView.ADD.class, CategoryView.EDIT.class, CategoryView.LIST.class})
    private String name;

    @JsonView({CategoryView.LIST.class})
    private int count;
}
