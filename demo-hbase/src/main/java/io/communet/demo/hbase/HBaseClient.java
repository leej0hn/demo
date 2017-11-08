package io.communet.demo.hbase;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * <p>function: hbase查询客户端
 * <p>User: LeeJohn
 * <p>Date: 2017/11/7
 * <p>Version: 1.0
 */
@Component
@Slf4j
public class HBaseClient {

    @Value("${hbaseProp.quorum:}")
    private String QUORUM ;

    @Value("${hbaseProp.clientport:}")
    private String CLIENTPORT ;
    // spring boot 扫描时与Configuration类冲突 ，不能出现在类变量里面
    private static Configuration conf = null;
    private static HConnection conn = null;
    private static HBaseAdmin admin = null;


    /**
     * 获取全局唯一的Configuration实例
     * @return
     */
    public static Configuration getConfiguration(String quorum ,String clientPort){
        if(conf == null){
            conf =  HBaseConfiguration.create();
            conf.set("hbase.zookeeper.quorum", quorum);
            conf.set("hbase.zookeeper.property.clientPort", clientPort);
//            conf.set("hbase.zookeeper.master", "cdh02:60000");
        }
        return conf;
    }

    /**
     * 获取全局唯一的HConnection实例
     * @return
     * @throws IOException
     */
    public HConnection getHConnection(){
        if(conn == null){
            try {
                conn = HConnectionManager.createConnection(getConfiguration(QUORUM,CLIENTPORT));
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return conn;
    }

    /**
     * 获取全局唯一的HBaseAdmin实例
     * @return
     * @throws ZooKeeperConnectionException
     * @throws MasterNotRunningException
     * @throws IOException
     */
    public HBaseAdmin getAdmin(){
        if(admin == null){
            try {
                admin = new HBaseAdmin(getHConnection());
            } catch (MasterNotRunningException | ZooKeeperConnectionException e) {
                log.error(e.getMessage());
            }
        }
        return admin;
    }

    /**
     * create a new Table
     * @param tableName String,the new Table's name
     * @throws IOException
     * */
    public void createTable(String tableName,String[] family) throws IOException{
        if(this.getAdmin().tableExists(tableName)){
            this.getAdmin().disableTable(tableName);
            this.getAdmin().deleteTable(tableName);
            log.info(tableName+"is exist ,delete ......");
        }
        HTableDescriptor tableDescriptor=new HTableDescriptor(TableName.valueOf(tableName));
        for(String s : family){
            tableDescriptor.addFamily(new HColumnDescriptor(s));
        }
        this.getAdmin().createTable(tableDescriptor);
        log.info("end create table");
    }

    /**
     * Table add family
     * @param tableName String,the Table's name
     * @throws IOException
     * */
    public void addFamily(String tableName,String[] family) throws IOException{
        if(!this.getAdmin().tableExists(tableName)){//表不存在
            log.error(tableName+"is not exist");
            return ;
        }
        for(String s : family){
            admin.addColumn(tableName, new HColumnDescriptor(s));
        }
        log.info("end add family");
    }

    /**
     * delete add family
     * @param tableName String,the Table's name
     * @throws IOException
     * */
    public void deleteFamily(String tableName,String[] family) throws IOException{
        if(!this.getAdmin().tableExists(tableName)){//表不存在
            log.error(tableName+"is not exist");
            return ;
        }
        for(String s : family){
            admin.deleteColumn(tableName, s);
        }
        log.info("end delete family");
    }

    /**
     * Delete the existing table
     * @param tableName
     *            String,Table's name
     * @throws IOException
     * */
    public void dropTable(String tableName) throws IOException {
        if (this.getAdmin().tableExists(tableName)) {
            this.getAdmin().disableTable(tableName);
            this.getAdmin().deleteTable(tableName);
            log.info(tableName + "delete success!");
        } else {
            log.info(tableName + "Table does not exist!");
        }
    }


    /**
     * insert a data
     * @param tableName
     * String,Table's name
     * @throws IOException
     * */
    public void addData(String tableName, String rowKey, String family,
                        String qualifier, String[] columns, String[] values) throws IOException {
        HTableInterface htable = null ;
        try {
            if (this.getAdmin().tableExists(tableName)) {
                htable = this.getHConnection().getTable(tableName);
                Put put = new Put(Bytes.toBytes(rowKey));
                for (int i = 0; i < columns.length; i++) {
                    put.add(Bytes.toBytes(family),Bytes.toBytes(String.valueOf(columns[i])),Bytes.toBytes(values[i]));
                    htable.put(put);
                }
                log.info("add success!");
            } else {
                log.info(tableName + "Table does not exist!");
            }
        }finally{
            if(htable!=null){
                htable.close();
            }
        }
    }

    /**
     * Delete a data
     *
     * @param tableName
     *            String,Table's name
     * @throws IOException
     * */
    public void deleteDate(String tableName, String rowKey) throws IOException {
        HTableInterface htable = null ;
        try {
            if (this.getAdmin().tableExists(tableName)) {
                htable = this.getHConnection().getTable(tableName);
                Delete delete = new Delete(Bytes.toBytes(rowKey));
                htable.delete(delete);
                log.info("delete success!");
            } else {
                log.info("Table does not exist!");
            }
        }finally{
            if(htable!=null){
                htable.close();
            }
        }
    }

    /**
     * 查找一行记录
     *
     * @param tablename
     *            表名
     * @param rowKey
     *            行名
     * @throws IOException
     */
    public Result getByRow(String tablename, String rowKey) throws IOException {
        HTableInterface htable = null ;
        Result rs = null;
        try {
            htable = this.getHConnection().getTable(tablename);
            Get g = new Get(rowKey.getBytes());
            rs = htable.get(g);
        }finally{
            if(htable!=null){
                htable.close();
            }
        }
        return rs;
    }


    /**
     * 查询表中所有行,
     * ResultScanner 用完需要关闭
     *
     * @param tablename
     * @throws IOException
     */
    public ResultScanner scan(String tablename, Scan scan) throws IOException {
        ResultScanner rscan = null;
        HTableInterface htable = null ;
        try {
            htable = this.getHConnection().getTable(tablename);
            if (scan == null) {
                scan = new Scan();
            }
            rscan = htable.getScanner(scan);
        } catch (IOException e) {
            log.error(e.getMessage());
        }finally{
            if(htable!=null){
                htable.close();
            }
        }
        return rscan;
    }
    /**
     * 增加一列数据到 hbase
     * @param tableName
     * @param rowKey
     * @param family
     * @param qualifier
     * @param value
     * @throws IOException
     */
    public void addValue(String tableName, String rowKey, String family,
                         String qualifier,String value) throws IOException {
        HTableInterface htable = null ;
        try {
            if (this.getAdmin().tableExists(tableName)) {
                htable = this.getHConnection().getTable(tableName);
                Put put = new Put(Bytes.toBytes(rowKey));
                put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
                log.info("add Value to " + tableName + " success!");
                htable.put(put);
            } else {
                log.info(tableName + "Table does not exist!");
            }
        }finally{
            if(htable!=null){
                htable.close();
            }
        }
    }

    public boolean has(String tableName, String rowKey, String family,
                       String qualifier) throws IOException {
        HTableInterface htable = null ;
        boolean flag = false;
        try {
            if (this.getAdmin().tableExists(tableName)) {
                htable = this.getHConnection().getTable(tableName);
                Get get = new Get(Bytes.toBytes(rowKey));
                get.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier));
                flag = htable.exists(get);
                return flag;
            } else {
                log.info(tableName + "Table does not exist!");
                return false;
            }
        }finally{
            if(htable!=null){
                htable.close();
            }
        }
    }

    /**
     * 保存或者更新信息
     *
     * @param family
     * @param tableName
     * @param rowKey
     * @param paramMap
     * @return
     * @throws Exception
     */
    public void saveOrUpdateOne(String family, String tableName, String rowKey, Map<String, Object> paramMap) throws Exception {
        HTableInterface table = null;
        try {
            table = getHConnection().getTable(TableName.valueOf(Bytes.toBytes(tableName)));
            Put put = new Put(Bytes.toBytes(rowKey));
            for (Map.Entry<String, Object> en : paramMap.entrySet()) {
                String key = en.getKey();
                Object value = en.getValue();
                byte[] byteValue = null;
                if (value != null) {
                    String valueOFString = String.valueOf(value.toString());
                    byteValue = Bytes.toBytes(valueOFString);
                    put.add(Bytes.toBytes(family), Bytes.toBytes(key), byteValue);
                }
            }
            table.put(put);
        } catch (Exception e){
            log.info(e.getMessage());
        }finally {
            if (table != null) {
                table.close();
            }
        }
    }

}
