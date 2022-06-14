package ru.omsu.imit.duplicateFinder;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
    private static DataBase db;

    Map<String, String> hashSumToFileName;
    MultiValuedMap<String, String> digestToFilePaths;

    private DataBase() {
        hashSumToFileName = new HashMap<>();
        digestToFilePaths = new ArrayListValuedHashMap<>();
    }

    public static DataBase getDB() {
        if (db == null) {
            db = new DataBase();
        }
        return db;
    }

    public void clear() {
        hashSumToFileName.clear();
        digestToFilePaths.clear();
    }

    public void insert(String digest, String fileAbsPath) throws DuplicateFinderException {
        /*
        if(hashSumToFileName.putIfAbsent(digest, fileAbsPath) != null) {
            throw new DuplicateFinderException(DuplicateFinderErrorCode.DUPLICATE_FILE);
        }
         */
        //return null;
        String value = hashSumToFileName.get(digest);

        if(value == null) {
            hashSumToFileName.put(digest, fileAbsPath);
        }
        else {
            throw new DuplicateFinderException(DuplicateFinderErrorCode.DUPLICATE_FILE);
        }
    }
}
