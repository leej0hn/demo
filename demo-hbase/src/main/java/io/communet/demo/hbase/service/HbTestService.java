package io.communet.demo.hbase.service;

import com.google.common.base.Throwables;
import io.communet.demo.common.model.HbTestModel;
import io.communet.demo.hbase.HBaseClient;
import io.communet.demo.hbase.utils.HbaseResultToModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.client.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>function: test服务
 * <p>User: LeeJohn
 * <p>Date: 2017/11/08
 * <p>Version: 1.0
 */
@Service
@Slf4j
public class HbTestService {
    @Autowired
    private HBaseClient hBaseClient;

    private String tableName = "test";
    private String family = "f";

    public HbTestModel findByRowKey(String rowKey) {
        try {
            Result result = hBaseClient.getByRow(tableName, rowKey);
            HbTestModel model = HbaseResultToModel.resultToModel(result,HbTestModel.class,family);
            return model;
        }catch (Exception e){
            log.error(Throwables.getStackTraceAsString(e));
        }
        return null;
    }

    public void save(HbTestModel model){
        try {
            String rowkey = model.getName();
            Map<String, Object> params = HbaseResultToModel.modelToMap(model);
            hBaseClient.saveOrUpdateOne(family, tableName, rowkey, params);
        }catch (Exception e){
            log.error(Throwables.getStackTraceAsString(e));
        }
    }

}
