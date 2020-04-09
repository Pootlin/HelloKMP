@file:JvmName("ViewModelExtensions")

package dev.epool.hellokmp.extensions

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import androidx.lifecycle.observe
import dev.epool.hellokmp.CommonFlow

@MainThread
inline fun <T> CommonFlow<T>.observe(
    lifecycleOwner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
) = asLiveData().observe(lifecycleOwner, onChanged)