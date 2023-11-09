package ru.omsu.imit.duplicateFinder;

public class Duplicate {
    private String digest;
    private String filePath;

    public Duplicate(String digest, String filePath) {
        this.digest = digest;
        this.filePath = filePath;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
