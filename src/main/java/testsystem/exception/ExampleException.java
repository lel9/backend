package testsystem.exception;

public final class ExampleException extends AppException {

    public ExampleException() {
    }

    @Override
    public String getMessage() {
        return "Заданы не все входные или выходные данные";
    }

}
