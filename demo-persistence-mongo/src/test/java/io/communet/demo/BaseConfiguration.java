package io.communet.demo;

import io.communet.demo.persistence.mongo.repository.impl.SimpleCustomerRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan(
        basePackages = {
                "io.communet.demo"
        }
)
@EnableMongoRepositories(repositoryBaseClass = SimpleCustomerRepository.class)
public class BaseConfiguration {
}
