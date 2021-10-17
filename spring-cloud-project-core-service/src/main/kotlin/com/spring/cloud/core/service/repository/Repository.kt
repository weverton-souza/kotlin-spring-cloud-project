package com.spring.cloud.core.service.repository

import com.spring.cloud.core.service.domain.DomainImpl
import org.springframework.data.jpa.repository.JpaRepository


interface Repository<E : DomainImpl, K> : JpaRepository<E, K>
