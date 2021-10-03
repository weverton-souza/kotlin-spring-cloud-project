package com.spring.cloud.store.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class StoreServiceApplication

fun main(args: Array<String>) {
	runApplication<StoreServiceApplication>(*args)
}
