package com.spring.cloud.core.service.service

import com.spring.cloud.core.service.domain.DomainImpl
import com.spring.cloud.core.service.exceptions.ResourceNotFoundException
import com.spring.cloud.core.service.mapper.DomainMapper
import com.spring.cloud.core.service.repository.Repository
import com.spring.cloud.core.service.to.DataTO
import java.io.Serializable
import java.util.Optional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


open class ServiceImpl<D : DataTO, K : Serializable> protected constructor(
    private val repository: Repository<DomainImpl, Any>,
    private val mapper: DomainMapper<DomainImpl, DataTO>
) : Service<D, K> {

    protected val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun saveOrUpdate(resource: D): DataTO {
        logger.info(
            "Method: {}.{} --- Level: Init --- \nresource: {}",
            this.javaClass.simpleName, this.javaClass.enclosingMethod, resource
        )

        val dataTO = this.mapper.toDTO(this.repository.save(this.mapper.toDomain(resource)))

        logger.info(
            "Method: {}.{} --- Level: End --- \n{}:{}",
            this.javaClass.simpleName, this.javaClass.enclosingMethod, dataTO.javaClass.simpleName, dataTO
        )
        return dataTO
    }

    override fun findById(id: K): DataTO {
        logger.info(
            "Method: {}.{} --- Level: Init --- ID: {}",
            this.javaClass.simpleName, this.javaClass.enclosingMethod, id
        )

        val optionalResource: Optional<DomainImpl> = repository.findById(id)

        val dataTO = mapper.toDTO(optionalResource.orElseThrow { ResourceNotFoundException() })

        logger.info(
            "Method: {}.{} --- Level: End --- \n{}: {}",
            this.javaClass.simpleName, this.javaClass.enclosingMethod, dataTO.javaClass.simpleName, dataTO
        )
        return dataTO
    }

    override fun findAll(pageable: Pageable): Page<DataTO> {
        logger.info(
            "Method: {}.{} --- Level: Init --- \npageable: {}",
            this.javaClass.simpleName, this.javaClass.enclosingMethod, pageable
        )

        val dataTOS = this.repository.findAll(pageable).map(this.mapper::toDTO)

        logger.info(
            "Method: {}.{} --- Level: End --- \n{}: {}",
            this.javaClass.simpleName, this.javaClass.enclosingMethod, dataTOS.javaClass.simpleName, dataTOS
        )

        return dataTOS
    }

    override fun delete(id: K) {
        logger.info(
            "Method: {}.{} --- Level: Init --- \nID: {}", this.javaClass.simpleName, this.javaClass.enclosingMethod, id
        )

        this.repository.delete(this.mapper.toDomain(this.findById(id)))

        this.logger.info("Method: delete --- Level: End")
    }
}