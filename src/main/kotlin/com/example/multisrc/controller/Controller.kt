package com.example.multisrc.controller

import com.example.multisrc.mongo.MongoEntity
import com.example.multisrc.mongo.MongoEntityRepository
import com.example.multisrc.postgres.PostgresEntity
import com.example.multisrc.postgres.PostgresRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 *  @author Gunwoo Kang
 */
@Controller
class Controller(
    private val postgresRepository: PostgresRepository,
    private val mongoEntityRepository: MongoEntityRepository
) {

    @RequestMapping("/")
    fun index(): String {
        val postgresEntity = PostgresEntity()
        val mongoEntity = MongoEntity()
        postgresRepository.save(postgresEntity)
        mongoEntityRepository.save(mongoEntity)
        return "index"
    }
}