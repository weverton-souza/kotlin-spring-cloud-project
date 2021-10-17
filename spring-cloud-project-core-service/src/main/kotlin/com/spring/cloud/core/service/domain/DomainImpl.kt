package com.spring.cloud.core.service.domain

import java.util.UUID
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import org.hibernate.annotations.GenericGenerator

@MappedSuperclass
open class DomainImpl : Domain {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    override lateinit var id: UUID

}
