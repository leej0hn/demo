package io.communet.demo.persistence.mongo.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.util.StringUtils;

import static java.util.Collections.singletonList;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/6/24
 * <p>Version: 1.0
 */
@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
    @Value("${spring.data.mongodb.host}")
    private String host;
    @Value("${spring.data.mongodb.port}")
    private int port;
    @Value("${spring.data.mongodb.database}")
    private String database;
    @Value("${spring.data.mongodb.username}")
    private String username;
    @Value("${spring.data.mongodb.password}")
    private String password;

    @Autowired
    private ApplicationContext appContext;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        if( StringUtils.hasLength(username) && StringUtils.hasLength(password) ){
            return new MongoClient(singletonList(new ServerAddress(host, port)), singletonList(MongoCredential.createCredential(username, database, password.toCharArray())));
        }else {
            return new MongoClient(singletonList(new ServerAddress(host, port)));
        }
    }

    @Override
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoDbFactory factory = mongoDbFactory();
        MongoMappingContext mongoMappingContext = new MongoMappingContext();
        mongoMappingContext.setApplicationContext(appContext);
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(factory), mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return new MongoTemplate(factory, converter);
    }

}
