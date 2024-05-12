package ru.omsu.imit.duplicateFinder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class FileUtils {
    private static final DataBase db = DataBase.getDB();

    public static void addDirPath(String dirPath) {
        db.addDirPath(dirPath);
    }

    public static void deleteDuplicate(Duplicate duplicate) throws DuplicateFinderException {
        //вернуть ошибку, если подал несуществующий путь(но это невозможно т.к. у нас просто кнопка,
        //хотя все же возможно, вдруг ты другой программой этот файл удалил
        //так что брось исключение
        Validator.correctFileName(duplicate.getFilePath());

        File file = new File(duplicate.getFilePath());
        db.deleteFile(duplicate);
        //if(db.deleteFile(path))
        file.delete();
    }

    public static void deleteFile(Duplicate sd) {
        File file = new File(sd.getFilePath());
        db.deleteFile(sd);
        //if(db.deleteFile(path))
        file.delete();
    }

    public static void deleteFileAfterMoving(Duplicate sd) {
        File file = new File(sd.getFilePath());
        db.deleteFileAfterMove(sd);
        //if(db.deleteFile(path))
        file.delete();
    }

    private static File copyFileInOtherDir(String filePath, String whereMoveDirectoryPath)
            throws IOException {
        //return new File(filePath);
        File oldFile = new File(filePath);
        //можно будет создать копию только в другой папке, ведь мы переносим файл,
        //поэтому допустимо юзать такое же название, как и у старого файла
        //что делать, если файл с таким именем уже существует в другой папке? пусть ос разбирается?
        //File newFile = new File(whereMoveDirectoryPath);
        //потоком перекопируй содержимое
        //File newFile = new File(oldFile.getName());
        String nameOfFile = oldFile.toPath().getFileName().toString();
        String absoluteNameOfNewFile = whereMoveDirectoryPath + "\\"+ nameOfFile;

        File newFile = new File(absoluteNameOfNewFile);
        Path bytes = Files.copy(oldFile.toPath(),newFile.toPath());
        return newFile;
    }

    private static void pasteFile(File file, String whereMoveDirectoryPath) {
        ///
    }



    public static String moveFile(Duplicate file, String whereMoveDirectoryPath)
            throws IOException, DuplicateFinderException {
        //брось исключение, если путь, куда хотим переместить файл, будет путем к файлу
        //если этот путь не будет существовать, но в конце будет папка, то просто создай
        // весь набор папок и добавь туда этот файл
        //copy->delete->paste

            File copiedFile = copyFileInOtherDir(file.getFilePath(), whereMoveDirectoryPath);
            //db.changeFilePath(file, copiedFile.getPath());
            deleteFileAfterMoving(file);
            return copiedFile.getAbsolutePath();
        /*
        if (db.isSubDirPathOfTheDirPath(whereMoveDirectoryPath)) {
            db.setPathInRow(file.getDigest(), file.getFilePath(), whereMoveDirectoryPath);
        }
        */
    }

    public static void deleteAllDuplicates(Duplicate mainFile) throws DuplicateFinderException {
        Collection<String> paths = new ArrayList<>(db.getFilesByDigest(mainFile.getDigest()));
        for(String path : paths) {//из-за того что пафс это ссылка на объект из db, который изменяется по время исполнения deleteDuplicate
            if(path.equals(mainFile.getFilePath())){
                continue;
            }
            Duplicate d = new Duplicate(mainFile.getDigest(), path, "not deleted");
            deleteDuplicate(d);
        }
    }
}
