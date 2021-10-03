package com.spring.cloud.core.service.util

import java.util.UUID


class Context(
    private var tenantId: UUID?,
    private var userId: UUID?
) {

    fun getTenantId(): UUID? {
        return tenantId
    }

    fun getUserId(): UUID? {
        return userId
    }
}
