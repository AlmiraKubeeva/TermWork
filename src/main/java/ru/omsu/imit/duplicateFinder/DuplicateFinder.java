package ru.omsu.imit.duplicateFinder;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.UUID;


public class DuplicateFinder {

    private static String selectedDirPath;
    private static final DataBase db = DataBase.getDB();
    private static int countOfFiles = 0;

    private static String getDigest(File file)
            throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        try (DigestInputStream dis
                     = new DigestInputStream(
                             new BufferedInputStream(new FileInputStream(file)), md);
        ) {

            while (dis.read() != -1) ;
            md = dis.getMessageDigest();

            StringBuilder result = new StringBuilder();
            for (byte b : md.digest()) {
                result.append(String.format("%02x", b));
            }

            return result.toString();
        }
    }

    public static int getCountOfFiles(String dirAbsPath)
            throws DuplicateFinderException, IOException, NoSuchAlgorithmException {
        Validator.correctFileName(dirAbsPath);
        Validator.checkIsDir(dirAbsPath);
        selectedDirPath = dirAbsPath;
        File dir = new File(dirAbsPath);
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                getCountOfFiles(file.getAbsolutePath());
            } else {
                countOfFiles += 1;
            }
        }

        return countOfFiles;
    }

    public static void addAllFilesFromDirToDB(String dirAbsPath)
            throws DuplicateFinderException, IOException, NoSuchAlgorithmException {
        Validator.correctFileName(dirAbsPath);
        Validator.checkIsDir(dirAbsPath);
        selectedDirPath = dirAbsPath;
        File dir = new File(dirAbsPath);
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    addAllFilesFromDirToDB(file.getAbsolutePath());
                } else {
                    String digest = DuplicateFinder.getDigest(file);
                    db.insert(digest, file.getAbsolutePath());
                    //add uuid
                }
            }
        }
    }
    public static Collection<Duplicate> showTable() {
        return db.showDuplicates();
    }

    public String getSelectedDirPath() {
        return selectedDirPath;
    }
}
