package testsystem.exception.zip;


public final class InvalidFileInZip extends ZipFileException {

    public InvalidFileInZip() { }

    @Override
    public String getMessage() {
        return "В архиве содержатся недопустимые файлы";
    }

}