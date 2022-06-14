package ru.omsu.imit.duplicateFinder;

import java.io.File;

public class DuplicateDeleter {
    public static void deleteDuplicate(String filePath) {
        File file = new File(filePath);
        file.delete();
    }
}
