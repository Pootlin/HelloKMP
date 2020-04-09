package dev.epool.hellokmp

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ActivityBindingProperty<out T : ViewDataBinding>(
    @LayoutRes private val resId: Int
) : ReadOnlyProperty<Activity, T> {

    private var binding: T? = null

    override operator fun getValue(thisRef: Activity, property: KProperty<*>): T =
        binding ?: DataBindingUtil.setContentView<T>(thisRef, resId).also { binding = it }

}

fun <T : ViewDataBinding> binding(@LayoutRes resId: Int) = ActivityBindingProperty<T>(resId)