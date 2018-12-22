package io.github.foodsearcher.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

//@Configuration
//@PropertySource(value = "classpath:application.properties")
//public class MongoConfig {
//    @Value("${mongo.ip}")
//    private String MONGO_IP;
//    @Value("${mongo.port}")
//    private int MONGO_PORT;
//    @Value("${mongo.dbname}")
//    private String DB_NAME;
//
//    @Bean
//    public MongoDbFactory mongoDbFactory() throws Exception {
//        return new SimpleMongoDbFactory(new MongoClient(MONGO_IP, MONGO_PORT), DB_NAME);
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate() throws Exception {
//        return new MongoTemplate(mongoDbFactory());
//    }
//}
