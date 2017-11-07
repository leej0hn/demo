package io.communet.demo.hbase;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/08/22
 * <p>Version: 1.0
 */
public class TestHbaseClientTest extends BaseDaoTest {

    @Autowired
    private HBaseClient hBaseClient;

    @Test
    public void testSave() throws Exception{
        String tableName = "test33";
        String rowKey = "001";
        String family = "f1";
        String qualifier = "name";
        String value = "LeeJohn";
//        String[] family = new String[]{"f1"};
//        hBaseClient.createTable("test33",family);
        hBaseClient.addValue(tableName,rowKey,family,qualifier,value);
        System.out.println("8888");
    }

    @Test
    public void testFind() throws Exception{
        String tableName = "test33";
        Scan scan = new Scan();
//        scan.setStartRow(Bytes.toBytes("001"));
//        scan.setStopRow(Bytes.toBytes("002"));

        scan.setId("001");
        ResultScanner resultScanner = hBaseClient.scan(tableName,scan);
        System.out.println(resultScanner);
        for (Result result : resultScanner) {
            String family = "f1";
            String qualifier = "name";
            String value = Bytes.toString(result.getValue(Bytes.toBytes(family), Bytes.toBytes(qualifier)));
            System.out.println("value : " + value);
        }
    }

    @Test
    public void delete(){
    }

}
