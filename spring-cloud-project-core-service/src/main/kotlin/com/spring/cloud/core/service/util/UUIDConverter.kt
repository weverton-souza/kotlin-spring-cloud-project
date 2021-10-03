package com.spring.cloud.core.service.util

import java.nio.ByteBuffer
import java.util.UUID
import java.util.regex.Pattern

class UUIDConverter {

    fun toUUID(bytes: ByteArray): UUID {
        val buffer = ByteBuffer.wrap(bytes)
        val firstLong = buffer.long
        val secondLong = buffer.long
        return UUID(firstLong, secondLong)
    }

    fun toUUID(uuid: String): UUID? {
        return if (uuid.isNotEmpty()) UUID.fromString(uuid) else null
    }

    fun toString(uuid: UUID): String {
        return uuid.toString()
    }

    fun toBytes(uuid: UUID): ByteArray {
        val buffer = ByteBuffer.wrap(ByteArray(16))
        buffer.putLong(uuid.mostSignificantBits)
        buffer.putLong(uuid.leastSignificantBits)
        return buffer.array()
    }

    fun toPlainString(uuid: UUID): String? {
        return uuid.toString().replace("-", "")
    }

    fun format(uuid: String): String {
        return uuid.replaceFirst(
            "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)".toRegex(),
            "$1-$2-$3-$4-$5"
        )
    }

    fun isFormatValid(uuid: String): Boolean {
        val pattern = Pattern.compile(
            "(\\p{XDigit}{8})-(\\p{XDigit}{4})-(\\p{XDigit}{4})-(\\p{XDigit}{4})-(\\p{XDigit}+)"
        )
        return pattern.matcher(uuid).matches()
    }
}
