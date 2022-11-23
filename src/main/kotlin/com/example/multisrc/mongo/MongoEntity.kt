package com.example.multisrc.mongo

import javax.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository

/**
 *  @author Gunwoo Kang
 */
@Document(collection = "mongoEntity")
class MongoEntity (
    @Id
    var id: String? = null,
    var status: Boolean = false
)

interface MongoEntityRepository : MongoRepository<MongoEntity, String>