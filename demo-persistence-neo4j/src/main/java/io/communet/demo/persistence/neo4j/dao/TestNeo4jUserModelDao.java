package io.communet.demo.persistence.neo4j.dao;


import io.communet.demo.common.model.neo4j.TestUserModel;
import io.communet.demo.persistence.neo4j.repository.CustomerNeo4jRepository;
import org.springframework.data.neo4j.annotation.Query;

import java.util.List;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/8/22
 * <p>Version: 1.0
 */
public interface TestNeo4jUserModelDao extends CustomerNeo4jRepository<TestUserModel, Long> {
    /* 其他案例语句
    //通过订单ID查询
    @Query("MATCH (taobao_order_list{order_id:{0}}) <- [r] - (n:mobile_pegging_wechat) WITH n MATCH p = (n) - [*0..1] - (m) RETURN p")
    MobilePeggingWechat findBytaobaoOrderListId(String orderId);

    //综合查询，订单ID，mobile, nickname
    @Query("MATCH (a:taobao_order_list) <- [r] - (n:mobile_pegging_wechat) WHERE a.order_id =~ {0} AND n.mobile =~ {1} AND n.nickname =~ {2} WITH n MATCH p = (n) - [*0..1] - (m) RETURN p")
    List<MobilePeggingWechat> multipleFind(String orderId , String mobile, String nickname);

    //通过授权粉丝 appidOpenid查询
    @Query("MATCH (a:ccrm_auth_fans) <- [r] - (n:mobile_pegging_wechat) WHERE a.appid_openid = {0}  WITH n MATCH p = (n) - [*0..1] - (m) RETURN p")
    MobilePeggingWechat findByAuthFansAppidOpenid(String appidOpenid);

    */
}
