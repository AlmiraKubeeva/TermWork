package ru.omsu.imit.duplicateFinder;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class DataBase {
    private static DataBase db;
    private final MultiValuedMap<String, String> digestToFilePaths;
    private final MultiValuedMap<String, String> deletedFilePaths;
    private final MultiValuedMap<String, Duplicate> digestToDuplicate;
    private final MultiValuedMap<UUID, Duplicate> UUIDToDuplicates;
    private String dirPath;

    private DataBase() {
        digestToFilePaths = new ArrayListValuedHashMap<>();
        deletedFilePaths = new ArrayListValuedHashMap<>();
        digestToDuplicate = new ArrayListValuedHashMap<>();
        UUIDToDuplicates = new ArrayListValuedHashMap<>();
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
        digestToDuplicate.clear();
        UUIDToDuplicates.clear();
    }

    public void addDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    public void insert(String digest, String fileAbsPath) {
        Duplicate dup = new Duplicate(digest, fileAbsPath, "not deleted");
        digestToFilePaths.put(digest, fileAbsPath);
        digestToDuplicate.put(digest, dup);
        UUIDToDuplicates.put(dup.getUuid(), dup);
    }

    public void setPathInRow(String digest, String oldPath, String newPath) {
        digestToFilePaths.removeMapping(digest, oldPath);
        insert(digest, newPath);
    }

    public void insertDeletedFiles(Duplicate dup) {
        deletedFilePaths.put(dup.getDigest(), dup.getFilePath());
    }

    public Collection<Duplicate> showDuplicates() {
        List<Duplicate> duplicates = new ArrayList<>();
        for (String key : digestToFilePaths.keySet()) {
            if ((digestToFilePaths.get(key).size() >= 2)) {
                digestToFilePaths.get(key).forEach(n -> duplicates.add(new Duplicate(key, n, "not deleted")));
            }
        }

        return duplicates;
    }

    public void deleteFile(Duplicate duplicate) {
        digestToFilePaths.removeMapping(duplicate.getDigest(), duplicate.getFilePath());
        insertDeletedFiles(duplicate);
        digestToDuplicate.removeMapping(duplicate.getDigest(), duplicate);
        UUIDToDuplicates.removeMapping(duplicate.getUuid(), duplicate);
    }

    public void deleteFileAfterMove(Duplicate duplicate) {
        digestToFilePaths.removeMapping(duplicate.getDigest(), duplicate.getFilePath());
        digestToDuplicate.removeMapping(duplicate.getDigest(), duplicate);
        UUIDToDuplicates.removeMapping(duplicate.getUuid(), duplicate);
    }

    public Collection<Duplicate> showAllFiles() {
        List<Duplicate> allFiles = new ArrayList<>();
        for (String key : deletedFilePaths.keySet()) {
            deletedFilePaths.get(key).forEach(n -> allFiles.add((new Duplicate(key, n, "deleted"))));
        }
        for (String key : digestToFilePaths.keySet()) {
            digestToFilePaths.get(key).forEach(n -> allFiles.add(new Duplicate(key, n, "not deleted")));
        }
        return allFiles;
    }

    public boolean isSubDirPathOfTheDirPath(String path) {
        int sizeOfDirPath = this.dirPath.length();
        String subString = path.substring(0, sizeOfDirPath);
        return this.dirPath == subString;
    }

    public Collection<String> getFilesByDigest(String digest) {
        return digestToFilePaths.get(digest);
    }

    public void changeFilePath(Duplicate d, String newFilePath) {
        d.setFilePath(newFilePath);
    }
}
