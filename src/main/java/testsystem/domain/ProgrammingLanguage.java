package testsystem.domain;

public enum ProgrammingLanguage {
    python, c, cpp;

    private String name;

    static {
        python.name = "python";
        c.name = "c";
        cpp.name = "c++";
    }

    @Override
    public String toString() {
        return name;
    }

}