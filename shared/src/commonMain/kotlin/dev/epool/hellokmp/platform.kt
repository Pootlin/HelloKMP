package dev.epool.hellokmp

import io.ktor.client.engine.HttpClientEngine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

expect fun platformName(): String

expect val httpClientEngine: HttpClientEngine

expect class CommonFlow<T>(flow: Flow<T>)

expect abstract class BaseViewModel() {
    val scope: CoroutineScope
    protected open fun onCleared()
}

expect interface Parcelable

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
expect annotation class Parcelize()