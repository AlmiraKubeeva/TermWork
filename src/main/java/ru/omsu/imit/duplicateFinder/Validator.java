package ru.omsu.imit.duplicateFinder;

import java.io.File;

public class Validator {
    public static void correctFileName(String fileName) throws DuplicateFinderException {
        if(fileName == null || "".equals(fileName)) {
            throw new DuplicateFinderException(DuplicateFinderErrorCode.INVALID_FILE_NAME);
        }
    }

    public static void fileIsExist(String fileName) throws DuplicateFinderException {
       // if(new File(fileName).)
    }

    public static void checkIsDir(String fileName) throws DuplicateFinderException {
        if(!new File(fileName).isDirectory()) {
            throw new DuplicateFinderException(DuplicateFinderErrorCode.FILE_IS_NOT_DIRECTORY);
        }
    }

    public static void checkIsNotDir(String fileName) throws DuplicateFinderException {
        if(new File(fileName).isDirectory()) {
            throw new DuplicateFinderException(DuplicateFinderErrorCode.FILE_IS_DIRECTORY);
        }
    }

}
