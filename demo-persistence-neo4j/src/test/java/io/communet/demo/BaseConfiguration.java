package io.communet.demo;

import io.communet.demo.persistence.neo4j.repository.impl.SimpleCustomerNeo4jRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan(
        basePackages = {
                "io.communet.demo"
        }
)
@EnableNeo4jRepositories(repositoryBaseClass = SimpleCustomerNeo4jRepository.class)
public class BaseConfiguration {
}
