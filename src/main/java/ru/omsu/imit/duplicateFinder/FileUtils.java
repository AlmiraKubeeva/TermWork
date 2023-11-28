package ru.omsu.imit.duplicateFinder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class FileUtils {
    private static final DataBase db = DataBase.getDB();

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

    public static void deleteFile(SortedDuplicate sd) {
        File file = new File(sd.getFilePath());
        db.deleteFile(sd);
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



    public static void moveFile(SortedDuplicate file, String whereMoveDirectoryPath)
            throws IOException, DuplicateFinderException {
        //брось исключение, если путь, куда хотим переместить файл, будет путем к файлу
        //если этот путь не будет существовать, но в конце будет папка, то просто создай
        // весь набор папок и добавь туда этот файл
        //copy->delete->paste

        File copiedFile = copyFileInOtherDir(file.getFilePath(), whereMoveDirectoryPath);
        deleteFile(file);
    }
}
