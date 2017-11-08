package io.communet.demo.hbase.utils;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/11/8
 * <p>Version: 1.0
 */
@Slf4j
public class HbaseResultToModel {

    public static <T> T resultToModel(Result result ,Class<T> modelClass ,String family) {
        try {
            byte[] familyByte = Bytes.toBytes(family);
            T obj = (T)modelClass.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                String value = Bytes.toString(result.getValue(familyByte, Bytes.toBytes(name)));
                field.set(obj, value);
            }
            return obj;
        }catch (Exception e){
            log.error(Throwables.getStackTraceAsString(e));
            return null;
        }
    }

    public static <T> Map<String,Object> modelToMap(T model) {
        try {
            Map<String,Object> modelMap = new HashedMap();
            Field[] fields = model.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(model);
                modelMap.put(name,value);
            }
            return modelMap;
        }catch (Exception e){
            log.error(Throwables.getStackTraceAsString(e));
            return null;
        }
    }
}
