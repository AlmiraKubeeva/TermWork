package ru.omsu.imit.duplicateFinder;

public class SortedDuplicate {
    private String typeFile;
    private String digest;
    private String filePath;

    public SortedDuplicate(String typeFile, String digest, String filePath) {
        this.typeFile = typeFile;
        this.digest = digest;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTypeFile() {
        return typeFile;
    }

    public void setTypeFile(String typeFile) {
        this.typeFile = typeFile;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }
}

