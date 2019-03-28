package testsystem.exception;

public final class NoSuchCategoryException extends AppException {

    public NoSuchCategoryException() { }

    @Override
    public String getMessage() {
        return "Категория не найдена";
    }

}