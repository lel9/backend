package testsystem.exception;

public final class SolutionFileIOError extends AppException {

    public SolutionFileIOError() { }

    @Override
    public String getMessage() {
        return "Ошибка чтения файла решения";
    }

}
