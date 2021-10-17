package com.spring.cloud.core.service.exceptions

class FieldExceptionDetails(
    private var field: String,
    private var fieldMessages: MutableList<String?>
)
