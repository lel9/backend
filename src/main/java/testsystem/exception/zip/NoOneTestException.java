package testsystem.exception.zip;

public final class NoOneTestException extends ZipFileException {

    public NoOneTestException() { }

    @Override
    public String getMessage() {
        return "Не найдено ни одного теста";
    }

}
