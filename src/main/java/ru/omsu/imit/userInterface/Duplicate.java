package ru.omsu.imit.userInterface;

public class Duplicate {
    private int hash;
    private String filePath;

    public Duplicate(int hash, String filePath) {
        this.hash = hash;
        this.filePath = filePath;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
