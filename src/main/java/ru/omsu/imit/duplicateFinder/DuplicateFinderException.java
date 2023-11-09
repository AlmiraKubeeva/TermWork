package ru.omsu.imit.duplicateFinder;

public class DuplicateFinderException extends Throwable {
    private DuplicateFinderErrorCode duplicateFinderErrorCode;

    public DuplicateFinderException(DuplicateFinderErrorCode duplicateFinderErrorCode) {
        this.duplicateFinderErrorCode = duplicateFinderErrorCode;
    }

    public DuplicateFinderErrorCode getErrorCode() {
        return this.duplicateFinderErrorCode;
    }

}
