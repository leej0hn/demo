package io.communet.demo.es.dao;

import io.communet.demo.common.model.es.TestFansEsModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>function:
 * <p>User: leejohn
 * <p>Date: 2017/11/28
 * <p>Version: 1.0
 */
@Repository
public interface TestFansEsDao extends ElasticsearchRepository<TestFansEsModel, String> {
}
