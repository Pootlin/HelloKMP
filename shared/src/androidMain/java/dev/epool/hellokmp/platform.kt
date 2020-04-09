package dev.epool.hellokmp

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import okhttp3.logging.HttpLoggingInterceptor

actual fun platformName() = "Android ${Build.VERSION.RELEASE}"

actual val httpClientEngine: HttpClientEngine by lazy {
    OkHttp.create {
        addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
    }
}

actual class CommonFlow<T> actual constructor(flow: Flow<T>) : Flow<T> by flow

actual abstract class BaseViewModel actual constructor() : ViewModel() {
    actual val scope: CoroutineScope = viewModelScope
    actual override fun onCleared() = super.onCleared()
}

actual typealias Parcelable = android.os.Parcelable
actual typealias Parcelize = kotlinx.android.parcel.Parcelize