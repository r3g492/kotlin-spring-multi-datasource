package com.example.multisrc.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

/**
 *  @author Gunwoo Kang
 */
@Configuration
@EnableMongoRepositories(basePackages = ["com.example.multisrc.mongo"])
class MongoConfig : AbstractMongoClientConfiguration() {

    override fun getDatabaseName(): String {
        return "SITE_INFO"
    }

    override fun mongoClient(): MongoClient {
        val connectionString = ConnectionString("mongodb://root:root@localhost:27017/root?authSource=admin")

        val mongoClientSettings = MongoClientSettings
            .builder()
            .applyConnectionString(connectionString)
            .build()
        return MongoClients.create(mongoClientSettings)
    }

}