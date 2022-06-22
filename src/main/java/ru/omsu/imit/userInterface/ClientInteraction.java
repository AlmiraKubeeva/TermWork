package ru.omsu.imit.userInterface;

import ru.omsu.imit.duplicateFinder.DataBase;

import java.util.Collection;
import java.util.Collections;

public class ClientInteraction {
    private String absolutePath;
    private Collection<String> resultFiles;

    public void setAbsolutePath(String path) {
        this.absolutePath = path;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public Collection<String> getResultFiles() {
        DataBase dataBase = new DataBase();
        return Collections.singleton("getResultFiles!");
    }
}