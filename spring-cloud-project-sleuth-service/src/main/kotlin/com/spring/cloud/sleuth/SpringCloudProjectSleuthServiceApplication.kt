package com.spring.cloud.sleuth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringCloudProjectSleuthServiceApplication

fun main(args: Array<String>) {
	runApplication<SpringCloudProjectSleuthServiceApplication>(*args)
}
