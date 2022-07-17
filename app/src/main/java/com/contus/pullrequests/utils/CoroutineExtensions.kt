package com.contus.pullrequests.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> asyncAwait(block: suspend CoroutineScope.() -> T): T {
    return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) { block() }
}