package com.uhu.agi.mongodb.yelp.project.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.util.Collection;
import java.util.Collections;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.uhu.agi.mongodb.yelp.project.repository")
public class MongoDBConfiguration extends AbstractMongoClientConfiguration
{

    @Override
    protected String getDatabaseName()
    {
        return "yelp";
    }

    @Override
    public MongoClient mongoClient()
    {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/yelp");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection getMappingBasePackages()
    {
        return Collections.singleton("com.uhu.agi.mongodb.yelp.project");
    }

    @Override
    public MongoTemplate mongoTemplate(MongoDatabaseFactory databaseFactory, MappingMongoConverter converter)
    {
        MongoTemplate mongoTemplate = super.mongoTemplate(databaseFactory, converter);
        mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
        
        return mongoTemplate;
    }

}
