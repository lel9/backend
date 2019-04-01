package testsystem.exception.zip;

public final class OpenFileException extends ZipFileException {

    public OpenFileException() { }

    @Override
    public String getMessage() {
        return "Ошибка открытия zip архива";
    }

}