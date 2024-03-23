package org.chosun.dodamduck.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.chosun.dodamduck.domain.model.ApiResult

class SafeApiResult<T> {
    private var onSuccess: (suspend (T) -> Unit)? = null
    private var onLoading: (suspend () -> Unit)? = null
    private var onError: (suspend (ApiResult.Error) -> Unit)? = null
    private var onException: (suspend (Throwable) -> Unit)? = null

    fun onSuccess(action: suspend (T) -> Unit) {
        onSuccess = action
    }

    fun onLoading(action: suspend () -> Unit) {
        onLoading = action
    }

    fun onError(action: suspend (ApiResult.Error) -> Unit) {
        onError = action
    }

    fun onException(action: suspend (Throwable) -> Unit) {
        onException = action
    }

    suspend fun process(flow: Flow<ApiResult<T>>, scope: CoroutineScope) {
        scope.launch {
            onLoading?.invoke()
            flow.collectLatest { result ->
                when (result) {
                    is ApiResult.Success -> onSuccess?.invoke(result.value)
                    is ApiResult.Error -> onError?.invoke(result)
                    is ApiResult.Exception -> onException?.invoke(result.exception)
                }
            }
        }
    }
}

inline fun <T> CoroutineScope.processApiResult(
    flow: Flow<ApiResult<T>>,
    init: SafeApiResult<T>.() -> Unit
): SafeApiResult<T> {
    val processor = SafeApiResult<T>().apply(init)
    launch {
        processor.process(flow, this)
    }
    return processor
}