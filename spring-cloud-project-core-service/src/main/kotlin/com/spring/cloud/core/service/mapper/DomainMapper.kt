package com.spring.cloud.core.service.mapper

import com.spring.cloud.core.service.domain.DomainImpl
import com.spring.cloud.core.service.to.DataTO

interface DomainMapper<E: DomainImpl, D: DataTO> {

    fun toDTO(domain: E): D

    fun toDomain(dto: D): E

    fun toPageDTO(items: List<E>): List<D>

}
