package io.communet.demo.utils.test;

import io.communet.demo.utils.FileUtils;
import io.communet.demo.utils.JsonFileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/11/13
 * <p>Version: 1.0
 */
public class FileUtilsTest {
    @Test
    public void testFileWriteString() {
        FileUtils.writeString("asdfadf", new File("C:\\Users\\LeeJohn\\Documents\\雷电模拟器\\Misc\\test.txt"));
    }

    @Test
    public void testJsonWriteString() {
        String path = "C:\\Users\\LeeJohn\\Documents\\雷电模拟器\\Misc";
        String filePath = "C:\\Users\\LeeJohn\\Documents\\雷电模拟器\\Misc\\test.txt";
        JsonFileUtils.put(path, filePath, "a", "123");
    }

    @Test
    public static void main(String[] arg) {
        File f = new File("1.txt");
        //向文件里写如"Hello"字符串.
        try {
            //要写入的数据转换成字节数组
            byte[] buf = "Hello".getBytes();
            //如果1.txt存在,则删除1.txt里面的内容,文本所有内容变为Hello
            //如果1.txt不存在,在新建1.txt文本,写入Hello
            FileOutputStream out = new FileOutputStream(f);
            out.write(buf);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        //读取文件中的内容。可在程序中单独使用,不用关心"写"是否存在.
        try {
            //只要f存在就可以读出f的内容,与写操作代码没有关联性.
            FileInputStream in = new FileInputStream(f);
            byte[] buf = new byte[1024];
            int len = in.read(buf);        //从流中读取内容
            String str = new String(buf, 0, len);
            System.out.println(str);    //打印f文件的内容.
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
