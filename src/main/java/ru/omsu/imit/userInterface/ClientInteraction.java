package ru.omsu.imit.userInterface;

import ru.omsu.imit.duplicateFinder.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

public class ClientInteraction {
    private String absolutePath;
    private Collection<String> resultFiles;

    public void addDirPath(String path) {
        FileUtils.addDirPath(path);
    }

    public void setAbsolutePath(String path) throws DuplicateFinderException, IOException, NoSuchAlgorithmException {
        this.absolutePath = path;
        DuplicateFinder.addAllFilesFromDirToDB(absolutePath);

    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public Collection<Duplicate> getResultFiles() {
        return DataBase.getDB().showDuplicates();
    }

    public Collection<Duplicate> getResultSortedFiles() {
        return DataBase.getDB().showAllFiles();
    }


    public void setDeletableDuplicate(Duplicate deletableDuplicate)
            throws DuplicateFinderException {
        FileUtils.deleteDuplicate(deletableDuplicate);
    }

    public String setMovableFile(Duplicate file, String movingFilePath)
            throws IOException, DuplicateFinderException {
        String s = FileUtils.moveFile(file, movingFilePath);
        return s;
    }

    public void removeAllDuplicates(Duplicate mainFile) throws DuplicateFinderException {
        FileUtils.deleteAllDuplicates(mainFile);
    }
}