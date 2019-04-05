package testsystem.exception;

public final class TestsystemRequestException extends AppException {

    @Override
    public String getMessage() {
        return "Не удалось отправить решение на проверку";
    }
}
