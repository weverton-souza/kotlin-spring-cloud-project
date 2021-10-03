package com.spring.cloud.core.service.datasource

import com.spring.cloud.core.service.util.Context
import com.spring.cloud.core.service.util.ContextHolder
import com.spring.cloud.core.service.util.UUIDConverter
import java.util.UUID
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component

@Component
class MultiTenantFilter: Filter {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val req = request as HttpServletRequest
        val tenant = req.getHeader(MultiTenant.TENANT_KEY)
        val user = req.getHeader(MultiTenant.USER_KEY)

        var tenantUUID: UUID? = null
        var userUUID: UUID? = null

        if (tenant.isNotEmpty() && UUIDConverter().isFormatValid(tenant)) {
            tenantUUID = UUIDConverter().toUUID(tenant)
        }

        if (user.isNotEmpty() && UUIDConverter().isFormatValid(user)) {
            userUUID = UUIDConverter().toUUID(user)
        }

        req.setAttribute(MultiTenant.TENANT_KEY, tenantUUID)
        req.setAttribute(MultiTenant.USER_KEY, userUUID)


        ContextHolder().context = Context(tenantUUID, userUUID)

        chain.doFilter(request, response)
    }
}
