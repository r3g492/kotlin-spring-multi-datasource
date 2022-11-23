package com.example.multisrc.postgres

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import org.springframework.data.jpa.repository.JpaRepository

/**
 *  @author Gunwoo Kang
 */
@Entity
@Table(name = "site_info")
class PostgresEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var postgresData: String? = null,
)

interface PostgresRepository : JpaRepository<PostgresEntity, Int>