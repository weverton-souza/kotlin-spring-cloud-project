package com.spring.cloud.core.service.datasource

object MultiTenant {
    const val TENANT_KEY = "X-Company-Tenant"
    const val DEFAULT_TENANT = "public"
    const val USER_KEY = "X-User"
}
