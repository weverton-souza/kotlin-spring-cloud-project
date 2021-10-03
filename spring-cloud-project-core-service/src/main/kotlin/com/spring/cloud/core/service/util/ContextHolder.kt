package com.spring.cloud.core.service.util

class ContextHolder {

    companion object {
        private val CONTEXT: ThreadLocal<Context> = ThreadLocal()
    }

    var context: Context?
        get() = CONTEXT.get()
        set(context) {
            CONTEXT.set(context)
        }

    fun clear() {
        CONTEXT.remove()
    }
}
