package io.communet.demo.utils;


import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * <p>function: 文件 base64编码
 * <p>User: LeeJohn
 * <p>Date: 2017/9/30
 * <p>Version: 1.0
 */

public class Base64Util {
    private static Logger logger = LoggerFactory.getLogger(Base64Util.class);
    /**
     * encodeBase64File:(将文件转成base64 字符串). <br/>
     * @param path 文件路径
     * @return
     * @since JDK 1.6
     */
    public static String encodeBase64File(String path) {
        if( path == null || path.equals("") ){
            return null;
        }
        FileInputStream inputFile = null;
        try{
            File file = new File(path);
            inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int)file.length()];
            inputFile.read(buffer);
            return Base64.encodeBase64String(buffer);
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                if( inputFile != null ){
                    inputFile.close();
                }
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * decoderBase64File:(将base64字符解码保存文件). <br/>
     * @param base64Code 编码后的字串
     * @param savePath  文件保存路径
     * @since JDK 1.6
     */
    public static void decoderBase64File(String base64Code,String savePath) {
        if( base64Code == null || base64Code.equals("") || savePath == null || savePath.equals("") ){
            return;
        }
        FileOutputStream out = null ;
        try {
            byte[] buffer = Base64.decodeBase64(base64Code);
            out = new FileOutputStream(savePath);
            out.write(buffer);
            out.flush();
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            if( out != null ){
                try{
                    out.close();
                }catch (Exception e){
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
