package testsystem.exception.zip;

import testsystem.exception.AppException;

public final class TestFileIOException extends AppException {

    public TestFileIOException() { }

    @Override
    public String getMessage() {
        return "Ошибка чтения файлов архива";
    }

}