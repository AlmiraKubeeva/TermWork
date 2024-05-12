package ru.omsu.imit.duplicateFinder;

import java.io.File;
import java.util.UUID;

public class Duplicate {
    private UUID uuid;
    private String typeFile;
    private String digest;
    private String filePath;

    public Duplicate(String digest, String filePath, String typeFile) {
        this.uuid = UUID.randomUUID();
        this.typeFile = typeFile;
        this.digest = digest;
        this.filePath = filePath;
    }

    public UUID getUuid() {
        return uuid;
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

    public String getTypeFile() {
        return typeFile;
    }

    public void setTypeFile(String typeFile) {
        this.typeFile = typeFile;
    }

}
