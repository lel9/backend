package testsystem.exception;

public final class UnknownLanguageException extends AppException {

    @Override
    public String getMessage() {
        return "Не удалось определить язык программирования по расширению файла";
    }
}
