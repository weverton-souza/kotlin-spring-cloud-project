package com.spring.cloud.core.service.datasource

import java.sql.Connection
import java.sql.SQLException
import java.util.logging.Level
import java.util.logging.Logger
import javax.sql.DataSource
import org.hibernate.HibernateException
import org.hibernate.cfg.AvailableSettings
import org.hibernate.engine.config.spi.ConfigurationService
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider
import org.hibernate.service.spi.ServiceRegistryAwareService
import org.hibernate.service.spi.ServiceRegistryImplementor
import org.springframework.stereotype.Component

@Component
class SchemaConnectionProvider: MultiTenantConnectionProvider, ServiceRegistryAwareService {

    private val logger = Logger.getLogger(SchemaConnectionProvider::class.java.name)

    @Transient
    private var dataSource: DataSource? = null

    override fun getAnyConnection(): Connection {
        return dataSource!!.connection
    }

    override fun releaseAnyConnection(connection: Connection) {
        connection.close()
    }

    override fun getConnection(tenantIdentifier: String): Connection {
        val connection = this.anyConnection
        try {
            connection.createStatement().execute("SET search_path to $tenantIdentifier")
            this.logger.log(Level.INFO, "Database schema set to $tenantIdentifier")
        } catch (e: SQLException) {
            throw HibernateException("Could not alter JDBC connection to specified schema [$tenantIdentifier]", e)
        }
        return connection
    }

    override fun releaseConnection(tenantIdentifier: String, connection: Connection) {
        try {
            connection.createStatement().execute("SET search_path to public")
        } catch (e: SQLException) {
            throw HibernateException("Could not alter JDBC connection to specified schema [$tenantIdentifier]", e)
        }

        connection.close()
    }

    override fun injectServices(serviceRegistry: ServiceRegistryImplementor) {
        dataSource =
            serviceRegistry
                .getService(ConfigurationService::class.java).settings[AvailableSettings.DATASOURCE] as DataSource?
    }

    override fun isUnwrappableAs(unwrapType: Class<*>): Boolean {
        return false
    }

    override fun <T : Any?> unwrap(unwrapType: Class<T>): T? {
        return null
    }

    override fun supportsAggressiveRelease(): Boolean {
        return true
    }
}