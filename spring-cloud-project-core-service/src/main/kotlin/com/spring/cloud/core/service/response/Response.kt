package com.spring.cloud.core.service.response

class Response<CONTENT>(
    val content: CONTENT?,
    val code: Int?,
    val message: String?
)

