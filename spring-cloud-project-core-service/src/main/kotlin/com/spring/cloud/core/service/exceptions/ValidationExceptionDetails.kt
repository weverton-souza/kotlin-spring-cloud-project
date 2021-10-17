package com.spring.cloud.core.service.exceptions

class ValidationExceptionDetails(
    private var fieldExceptionDetails: List<FieldExceptionDetails>,
    private var timestamp: Long,
    private var developerMessage: String
) : ExceptionDetails()
