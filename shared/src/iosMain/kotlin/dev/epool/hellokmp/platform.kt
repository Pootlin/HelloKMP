package dev.epool.hellokmp

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.ios.Ios
import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import platform.UIKit.UIDevice

actual fun platformName() = "${UIDevice.currentDevice.systemName()} ${UIDevice.currentDevice.systemVersion}"

actual val httpClientEngine: HttpClientEngine by lazy { Ios.create() }

actual class CommonFlow<T> actual constructor(flow: Flow<T>) : Flow<T> by flow {

    fun observe(block: (T) -> Unit): Closeable {
        val job = Job()

        onEach {
            block(it)
        }.launchIn(CoroutineScope(IosMainDispatcher + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }

}

actual abstract class BaseViewModel actual constructor() {
    actual val scope: CoroutineScope = CoroutineScope(IosMainDispatcher + SupervisorJob())
    protected actual open fun onCleared() = scope.cancel()
}

actual interface Parcelable

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
actual annotation class Parcelize