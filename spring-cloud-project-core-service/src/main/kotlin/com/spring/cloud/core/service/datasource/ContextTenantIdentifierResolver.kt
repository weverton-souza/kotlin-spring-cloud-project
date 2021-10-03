package com.spring.cloud.core.service.datasource

import com.spring.cloud.core.service.util.Context
import com.spring.cloud.core.service.util.ContextHolder
import com.spring.cloud.core.service.util.UUIDConverter
import java.util.UUID
import org.hibernate.context.spi.CurrentTenantIdentifierResolver
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder

class ContextTenantIdentifierResolver: CurrentTenantIdentifierResolver {

    override fun resolveCurrentTenantIdentifier(): String {
        val requestAttributes: RequestAttributes? = RequestContextHolder.getRequestAttributes()
        if (requestAttributes != null) {
            val tenantId =
                requestAttributes.getAttribute(MultiTenant.TENANT_KEY, RequestAttributes.SCOPE_REQUEST)!! as UUID
            return toSchemaString(tenantId)
        }

        val context: Context? = ContextHolder().context

        return if (context?.getTenantId() != null) {
            toSchemaString(context.getTenantId()!!)
        } else MultiTenant.DEFAULT_TENANT

    }

    private fun toSchemaString(tenantId: UUID): String {
        return "_" + UUIDConverter().toPlainString(tenantId)
    }

    override fun validateExistingCurrentSessions(): Boolean = true
}
