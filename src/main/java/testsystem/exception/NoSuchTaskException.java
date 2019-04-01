package testsystem.exception;

public final class NoSuchTaskException extends AppException {

    public NoSuchTaskException() { }

    @Override
    public String getMessage() {
        return "Задача не найдена";
    }

}