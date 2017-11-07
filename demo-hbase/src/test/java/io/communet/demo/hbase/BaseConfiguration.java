package io.communet.demo.hbase;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(
        basePackages = {
                "io.communet.demo"
        }
)
public class BaseConfiguration {
}
