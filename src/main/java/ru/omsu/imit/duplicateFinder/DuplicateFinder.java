package ru.omsu.imit.duplicateFinder;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.MD5;


public class DuplicateFinder {
    private static final DataBase db = DataBase.getDB();

    public static String getDigest(File file) {
        try {
            return new DigestUtils(MD5).digestAsHex(file);
        } catch (IOException ex) {
            //throw new DuplicateFinderException(Duplicate)
            return "Error with MD5";
        }
    }

    public static byte[] getDigest(byte[] bytes) {
        // try {
        return new DigestUtils(MD5).digest(bytes);
            /*
        }
        catch(IOException ex) {
            return new byte[0];
        }*/
    }

    // private static byte[] getFileContents(File file) {
    /*reading file contents*//*
        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(file))) {
            return bis.readAllBytes();
        } catch (FileNotFoundException e) {
            return new byte[0];
        } catch (IOException e) {
            return null;
        }

    }*/

/*
    private static String getFileContents(String path) throws IOException {
        File file = new File(path);
        StringBuilder line = null;
        int len = 0;
        String str;
//        try(BufferedReader br = new BufferedReader(new FileReader(file, UTF_8))) {
        try(BufferedReader br = new BufferedReader(new FileReader(file, UTF_8))) {


            while ((str = br.readLine()) != null && !"".equals(str)){
                len = len + line.length();
                line.append(str);
            }
            return line.toString();
        }
    }
*/
    /*
    public static String rec(String dirName) {
        try {
            Validator.correctFileName(dirName);
            Validator.checkIsDir(dirName);

            File file = new File(dirName);
            File[] files = file.listFiles();
            //byte[] fileContents = getFileContents(file);
            //byte[] digest = DuplicateFinder.getDigest(fileContents);
            if(files == null) {
                throw new DuplicateFinderException(DuplicateFinderErrorCode.FILES_NULL);
            }
            for(File file1:files) {
                //Validator.checkIsDir(file1.getAbsolutePath());
                //String fileContent = getFileContents(file1.getAbsolutePath());

                //byte[] fileContentsByte = getFileContents(file1);
                //byte[] digest = DuplicateFinder.getDigest(fileContentsByte);
                //db.insert(digest.toString(), file1.getAbsolutePath());
//recursion in this cycle
                if(file1.isDirectory()) {
                    rec(file1.getAbsolutePath());
                }
                String digest = DuplicateFinder.getDigest(file1);//if error

                db.insert(digest, file1.getAbsolutePath());
            }
            return "";
        //} catch(DuplicateFinderException | IOException ex) {
        } catch(DuplicateFinderException ex) {
            return ex.toString();
        }
    }

     */

    public static void DuplicateFound() {
        //вывести сообщение и предложить пользователю удалить файл,
        // а мб сразу удалить, тогда этот метод не нужен
    }

    public static void rec(String dirName) throws DuplicateFinderException {
        //LayoutController layoutController = new LayoutController();

        Validator.correctFileName(dirName);
        Validator.checkIsDir(dirName);

        File file = new File(dirName);
        File[] files = file.listFiles();

        if (files != null) {
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    rec(file1.getAbsolutePath());
                } else {
                    String digest = DuplicateFinder.getDigest(file1);
                    try {
                        db.insert(digest, file1.getAbsolutePath());
                    } catch(DuplicateFinderException ex) {
                        //DuplicateDeleter.deleteDuplicate(file1.getAbsolutePath());
                        if(!file1.delete()) {
                            throw new DuplicateFinderException(
                                    DuplicateFinderErrorCode.FILE_CANNOT_DELETE);
                        }
                    }
                }
            }
        }
    }


}
