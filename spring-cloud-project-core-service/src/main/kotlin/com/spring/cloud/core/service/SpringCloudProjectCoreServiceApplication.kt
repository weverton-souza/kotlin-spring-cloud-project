package com.spring.cloud.core.service

import com.spring.cloud.core.service.util.UUIDConverter
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.util.UUID
import java.util.logging.Level
import java.util.logging.Logger
import javax.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

@Service("application")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@ComponentScan(basePackages = ["com.spring.cloud.core.service", "com.spring.cloud.*"])
class SpringCloudProjectCoreServiceApplication{

	/**
	 * The logger instance for this class
	 */
	private val logger = Logger.getLogger(SpringCloudProjectCoreServiceApplication::class.java.name)

	@Value("\${spring.application.name}")
	private val name: String? = null

	private var instance: UUID? = null

	@PostConstruct
	private fun initialize() = generateInstanceId()

	private fun generateInstanceId() {
		try {
			val file = File("application-instance.dat")
			if (file.exists()) {
				val fileReader = FileReader(file)
				val bufferedReader = BufferedReader(fileReader)
				val line = bufferedReader.readLine()
				instance = UUIDConverter().toUUID(line)
				bufferedReader.close()
			} else {
				instance = UUID.randomUUID()
				val fileWriter = FileWriter(file)
				val bufferedWriter = BufferedWriter(fileWriter)
				bufferedWriter.write(instance.toString())
				bufferedWriter.flush()
				bufferedWriter.close()
				fileWriter.close()
			}
			logger.log(Level.INFO, "####################################################################")
			logger.log(Level.INFO, "############### {0} ###############", instance)
			logger.log(Level.INFO, "####################################################################")
		} catch (ex: IOException) {
			logger.log(
				Level.SEVERE,
				"\"############### ERROR: Cannot generate the INSTANCE ID to this application. ###############.",
				ex
			)
		}
	}

	fun getInstance(): UUID? = instance

	fun getName(): String? = name
}
