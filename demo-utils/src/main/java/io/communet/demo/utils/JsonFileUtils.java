package io.communet.demo.utils;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>function: 用于应用间共享数据，保存Json格式数据，基于文件操作工具类。
 * <p>User: LeeJohn
 * <p>Date: 2017/11/13
 * <p>Version: 1.0
 */
@Slf4j
public class JsonFileUtils {

    public static void put(String path , String fileNamePath , String key ,String value){
        try {
            String jsonStr = "";
            //创建文件夹
            FileUtils.createIsNotExists(path);
            //创建文件
            File file = new File(fileNamePath);
            if( !file.exists() ){
                file.createNewFile();
            }else{
                jsonStr = FileUtils.readString(file);
            }
            Map<String,String> configMap ;
            try {
               configMap = JSONObject.parseObject(jsonStr,Map.class);
            }catch (Exception e){
                configMap = new HashMap<>();
            }
            configMap.put(key,value);
            jsonStr = JSONObject.toJSONString(configMap);
            FileUtils.writeString(jsonStr,file);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public static String get(String filePath ,String key){
        String value = "";
        try{
            File file = new File(filePath);
            if( file.exists() ){
                String jsonStr = FileUtils.readString(file);
                Map<String,String> configMap = JSONObject.parseObject(jsonStr,Map.class);
                value = configMap.get(key);
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return value;
    }

}
