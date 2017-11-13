package io.communet.demo.utils.test;

import io.communet.demo.utils.FileUtils;
import io.communet.demo.utils.JsonFileUtils;
import org.junit.Test;

import java.io.File;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/11/13
 * <p>Version: 1.0
 */
public class FileUtilsTest {
    @Test
    public void testFileWriteString(){
        FileUtils.writeString("asdfadf",new File("C:\\Users\\LeeJohn\\Documents\\雷电模拟器\\Misc\\test.txt"));
    }

    @Test
    public void testJsonWriteString(){
        String path = "C:\\Users\\LeeJohn\\Documents\\雷电模拟器\\Misc";
        String filePath = "C:\\Users\\LeeJohn\\Documents\\雷电模拟器\\Misc\\test.txt";
        JsonFileUtils.put(path,filePath,"a","123");
    }
}
