package com.spring.cloud.core.service.service

import com.spring.cloud.core.service.to.DataTO

import java.io.Serializable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface Service<D : DataTO, K : Serializable> {

    fun saveOrUpdate(resource: D): DataTO

    fun findById(id: K): DataTO

    fun findAll(pageable: Pageable): Page<DataTO>

    fun delete(id: K)

}
