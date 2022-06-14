package ru.omsu.imit.duplicateFinder;

public enum DuplicateFinderErrorCode {
    INVALID_FILE_NAME("File name is null or empty string"),
    FILE_IS_NOT_DIRECTORY("FILE_IS_NOT_DIRECTORY"),
    FILES_NULL("FILES_NULL"),
    FILE_IS_DIRECTORY("FILE_IS_DIRECTORY"),
    DUPLICATE_FILE("DUPLICATE_FILE"),
    FILE_CANNOT_DELETE("FILE_CANNOT_DELETE");

    private final String errorMessage;

    DuplicateFinderErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
