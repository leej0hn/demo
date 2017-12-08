package io.communet.demo.es;

import io.communet.demo.common.model.es.TestAcct;
import io.communet.demo.common.model.es.TestFansEsModel;
import io.communet.demo.common.model.es.TestUserEsModel;
import io.communet.demo.es.dao.TestFansEsDao;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
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
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;


/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/12/08
 * <p>Version: 1.0
 */
public class ESFansTestServiceTest extends BaseDaoTest {

    @Autowired
    TestFansEsDao testFansEsDao;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Test
    public void testRunning(){
        System.out.println("runnig");
    }

    @Test
    public void save(){
        testFansEsDao.deleteAll();

        TestAcct acct1 = new TestAcct();
        acct1.setAppName("公众号1");
        acct1.setAppId("wxId_1");
        TestAcct acct2 = new TestAcct();
        acct2.setAppName("公众号2");
        acct2.setAppId("wxId_2");
        TestAcct acct3 = new TestAcct();
        acct3.setAppName("公众号3");
        acct3.setAppId("wxId_3");

        TestFansEsModel fans1 = new TestFansEsModel();
        fans1.setId("fan_1");
        fans1.setCity("GZ");
        fans1.setCompId("comp1");
        List<TestAcct> acctList1 = new ArrayList<>();
        acctList1.add(acct3);
        fans1.setAcctList(acctList1);
        testFansEsDao.save(fans1);


        TestFansEsModel fans2 = new TestFansEsModel();
        fans2.setId("fan_2");
        fans2.setCity("GZ");
        fans2.setCompId("comp1");
        List<TestAcct> acctList2 = new ArrayList<>();
        acctList2.add(acct1);
        fans2.setAcctList(acctList2);
        testFansEsDao.save(fans2);

        TestFansEsModel fans3 = new TestFansEsModel();
        fans3.setId("fan_3");
        fans3.setCity("GZ");
        fans3.setCompId("comp1");
        List<TestAcct> acctList3 = new ArrayList<>();
        acctList3.add(acct2);
        acctList3.add(acct3);
        fans3.setAcctList(acctList3);
        testFansEsDao.save(fans3);

        TestFansEsModel fans4 = new TestFansEsModel();
        fans4.setId("fan_4");
        fans4.setCity("SZ");
        fans4.setCompId("comp1");
        List<TestAcct> acctList4 = new ArrayList<>();
        acctList4.add(acct1);
        acctList4.add(acct3);
        fans4.setAcctList(acctList4);
        testFansEsDao.save(fans4);

        TestFansEsModel fans5 = new TestFansEsModel();
        fans5.setId("fan_5");
        fans5.setCity("SZ");
        fans5.setCompId("comp1");
        List<TestAcct> acctList5 = new ArrayList<>();
        acctList5.add(acct1);
        acctList5.add(acct2);
        fans5.setAcctList(acctList5);
        testFansEsDao.save(fans5);
    }

    @Test
    public void testFindAll(){
        Pageable pageable = new PageRequest(0,10);
        AggregatedPage<TestFansEsModel> all = (AggregatedPage)testFansEsDao.findAll(pageable);
        System.out.println(all.getContent());
    }

    /**
     * 聚合查询
     */
    @Test
    public void testAggsFind(){
        Pageable pageable = new PageRequest(0,100);
        BoolQueryBuilder boolQueryBuilder = boolQuery();
//        boolQueryBuilder.must(QueryBuilders.termQuery("city","gz"));
        boolQueryBuilder.should(QueryBuilders.termQuery("city","gz"));
        boolQueryBuilder.must(QueryBuilders.termQuery("city","sz"));
//        boolQueryBuilder.should(QueryBuilders.termQuery("city","sz"));
        //类似于in查询
//        TermsBuilder termsBuilder = AggregationBuilders.terms("term_city").field("city");
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
//                .addAggregation(termsBuilder)
                .withPageable(pageable)
                .build();

        System.out.println(searchQuery.getQuery().toString());
        List<TestFansEsModel> testFansEsModels = elasticsearchTemplate.queryForList(searchQuery, TestFansEsModel.class);
        System.out.println(testFansEsModels);
        System.out.println(testFansEsModels.size());
//        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Aggregations>(){
//            @Override
//            public Aggregations extract(SearchResponse response) {
//                Aggregations aggregations = response.getAggregations();
//                InternalTerms internalTerms = (InternalTerms) aggregations.asList().get(0);
//                List<Terms.Bucket> buckets = internalTerms.getBuckets();
//                for (Terms.Bucket buket : buckets) {
//                    System.out.println("key :　" + buket.getKeyAsString());
//                    System.out.println("docCount :　" + buket.getDocCount());
//                }
//                return  response.getAggregations();
//            }
//        });

    }

    @Test
    public void testDeleteAll(){
        testFansEsDao.deleteAll();
    }



}
