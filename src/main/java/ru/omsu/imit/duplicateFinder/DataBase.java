package ru.omsu.imit.duplicateFinder;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataBase {
    private static DataBase db;

    private final MultiValuedMap<String, String> digestToFilePaths;
    private final MultiValuedMap<String, String> deletedFilePaths;

    private DataBase() {
        digestToFilePaths = new ArrayListValuedHashMap<>();
        deletedFilePaths = new ArrayListValuedHashMap<>();
    }

    public static synchronized DataBase getDB() {
        if (db == null) {
            db = new DataBase();
        }
        return db;
    }

    public void clear() {
        digestToFilePaths.clear();
        deletedFilePaths.clear();
    }

    public void insert(String digest, String fileAbsPath) {
        digestToFilePaths.put(digest, fileAbsPath);
    }

    public void insertDeletedFiles(Duplicate dup) {
        deletedFilePaths.put(dup.getDigest(), dup.getFilePath());
    }

    public Collection<Duplicate> showDuplicates() {
        List<Duplicate> duplicates = new ArrayList<>();
        for (String key : digestToFilePaths.keySet()) {
            if ((digestToFilePaths.get(key).size() >= 2)) {
                digestToFilePaths.get(key).forEach(n -> duplicates.add(new Duplicate(key, n)));
            }
        }
        return duplicates;
    }

    public Collection<SortedDuplicate> showAllFiles() {
        List<SortedDuplicate> allFiles = new ArrayList<>();
        for (String key : deletedFilePaths.keySet()) {
            deletedFilePaths.get(key).forEach(n -> allFiles.add((new SortedDuplicate("deleted", key, n))));
        }
        for (String key : digestToFilePaths.keySet()) {
            digestToFilePaths.get(key).forEach(n -> allFiles.add(new SortedDuplicate("not deleted", key, n)));
        }
        return allFiles;
    }

    public void deleteFile(Duplicate duplicate) {
        digestToFilePaths.removeMapping(duplicate.getDigest(), duplicate.getFilePath());
        insertDeletedFiles(duplicate);
    }
}
