package com.example.multisrc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MultisrcApplication

fun main(args: Array<String>) {
	runApplication<MultisrcApplication>(*args)
}
