package io.communet.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * <p>function: 操作文件工具类
 * <p>User: LeeJohn
 * <p>Date: 2017/08/21
 * <p>Version: 1.0
 */
@Slf4j
public class FileUtils {


    public static boolean isExists(String path){
        File file = new File(path);
        if ( file.exists() ){
            return true;
        }
        return false;
    }

    public static void createIsNotExists(String path){
        File file = new File(path);
        if ( !file.exists() ){
           file.mkdirs();
        }
    }

    public static void deleteAllFilesOfDir(File path) {
        if (!path.exists()) {
            return;
        }
        if (path.isFile()) {
            path.delete();
            return;
        }
        File[] files = path.listFiles();
        if( files != null ){
            for (int i = 0; i < files.length; i++) {
                deleteAllFilesOfDir(files[i]);
            }
        }
        path.delete();
    }

    /**
     * 复制单个文件
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }catch (Exception e) {
            log.error("复制单个文件操作出错 : " + e.getMessage());
        }
    }
    public static String readString(String path){
        File file=new File(path);
        return readString(file);
    }

    public static String readString(File file){
        String str="";
        try {
            FileInputStream in=new FileInputStream(file);
            // size  为字串的长度 ，这里一次性读完
            int size=in.available();
            byte[] buffer=new byte[size];
            in.read(buffer);
            in.close();
            str=new String(buffer,"utf-8");

        } catch (IOException e) {
            return null;
        }
        return str;
    }

    /**
     * 文件平夹需要存在，否则抛异常
     * @param str
     * @param file
     */
    public static void writeString(String str , File file){
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.print(str);// 往文件里写入字符串
        } catch (Exception e) {
            log.error("Xposed",e.getMessage());
        }
    }
}
