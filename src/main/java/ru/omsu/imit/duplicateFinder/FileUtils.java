package ru.omsu.imit.duplicateFinder;

import java.io.File;

public class FileUtils {
    private static final DataBase db = DataBase.getDB();

    public static void deleteFile(Duplicate duplicate) throws DuplicateFinderException {
        //вернуть ошибку, если подал несуществующий путь(но это невозможно т.к. у нас просто кнопка,
        //хотя все же возможно, вдруг ты другой программой этот файл удалил
        //так что брось исключение
        Validator.correctFileName(duplicate.getFilePath());

        File file = new File(duplicate.getFilePath());
        db.deleteFile(duplicate);
        //if(db.deleteFile(path))
        file.delete();
    }

    private static File copyFileInOtherDir(String filePath, String whereMoveDirectoryPath) {
        //return new File(filePath);
        File oldFile = new File(filePath);
        //можно будет создать копию только в другой папке, ведь мы переносим файл,
        //поэтому допустимо юзать такое же название, как и у старого файла
        //что делать, если файл с таким именем уже существует в другой папке? пусть ос разбирается?
        File newFile = new File(whereMoveDirectoryPath);
        //потоком перекопируй содержимое
        return null;
    }

    private static void pasteFile(File file, String whereMoveDirectoryPath) {
        ///
    }

    public static void moveFile(String filePath, String whereMoveDirectoryPath) {
        //брось исключение, если путь, куда хотим переместить файл, будет путем к файлу
        //если этот путь не будет существовать, но в конце будет папка, то просто создай
        // весь набор папок и добавь туда этот файл
        //copy->delete->paste
        File copiedFile = copyFileInOtherDir(filePath, whereMoveDirectoryPath);
    }
}
