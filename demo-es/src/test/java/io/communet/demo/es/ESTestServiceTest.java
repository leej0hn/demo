package io.communet.demo.es;

import io.communet.demo.common.model.es.TestUserEsModel;
import io.communet.demo.es.dao.TestEsDao;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.InternalTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;


/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/11/28
 * <p>Version: 1.0
 */
public class ESTestServiceTest extends BaseDaoTest {

    @Autowired
    TestEsDao testEsDao;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Test
    public void testRunning(){
        System.out.println("runnig");
    }

    @Test
    public void testFindAll(){
        Pageable pageable = new PageRequest(0,10);
        Iterable<TestUserEsModel> all = testEsDao.findAll(pageable);
        TestUserEsModel next = all.iterator().next();
        System.out.println(next);
    }

    /**
     * 聚合查询
     */
    @Test
    public void testAggsFind(){
        Pageable pageable = new PageRequest(0,1);
        TermsBuilder termsBuilder = AggregationBuilders.terms("term_subject").field("subject")
                .subAggregation(AggregationBuilders.sum("sum_age").field("age"));
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(boolQueryBuilder)
                .addAggregation(termsBuilder)
                .withPageable(pageable)
                .build();

//        System.out.println(searchQuery.getQuery().toString());
        Page<TestUserEsModel> models = testEsDao.search(searchQuery);
        System.out.println(models.getContent());
        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Aggregations>(){
            @Override
            public Aggregations extract(SearchResponse response) {
                Aggregations aggregations = response.getAggregations();
                InternalTerms createTimeInternalTerms = (InternalTerms) aggregations.asList().get(0);
                List<Terms.Bucket> createTimeBuckets = createTimeInternalTerms.getBuckets();
                for (Terms.Bucket createTimeBucket : createTimeBuckets) {
                    System.out.println("key :　" + createTimeBucket.getKeyAsString());
                    System.out.println("docCount :　" + createTimeBucket.getDocCount());
                    System.out.println("sum_age :　" + ((InternalSum)createTimeBucket.getAggregations().getProperty("sum_age")).getValue());
                }
                return  response.getAggregations();
            }
        });

    }


}
