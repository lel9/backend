package testsystem.dto.validator;

import testsystem.dto.TaskNewDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExamplesConstraintValidator implements ConstraintValidator<ExamplesConstraint, TaskNewDTO> {
    @Override
    public boolean isValid(TaskNewDTO taskNewDTO, ConstraintValidatorContext constraintValidatorContext) {
        return (taskNewDTO.getInputs() == null && taskNewDTO.getOutputs() == null) ||
                (taskNewDTO.getInputs() != null && taskNewDTO.getOutputs() != null &&
                        taskNewDTO.getInputs().size() == taskNewDTO.getOutputs().size());
    }
}
