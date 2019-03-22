package testsystem.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import testsystem.dto.IResultResponse;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class GenericResponse {

    private IResultResponse response;
    private List<ErrorResponse> errors = new ArrayList<>();

    public GenericResponse(IResultResponse response) {
        this.response = response;
    }

    public GenericResponse(List<ErrorResponse> errors) {
        this.errors = new ArrayList<>(errors);
    }

    public void addError(ErrorResponse error) {
        errors.add(error);
    }
}
