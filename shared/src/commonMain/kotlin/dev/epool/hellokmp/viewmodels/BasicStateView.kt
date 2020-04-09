package dev.epool.hellokmp.viewmodels

import dev.epool.hellokmp.Parcelable
import dev.epool.hellokmp.Parcelize

interface BaseStateView<T> {

    val basicStateView: BasicStateView

    val isLoading: Boolean
        get() = basicStateView.isLoading

    val hasError: Boolean
        get() = basicStateView.throwable != null

    val errorMessage: String
        get() = basicStateView.throwable?.message.orEmpty()

    fun copyBasic(basicStateView: BasicStateView): T

    fun toLoading(isLoading: Boolean) = copyBasic(basicStateView.copy(isLoading = isLoading, throwable = null))

    fun toError(throwable: Throwable) = copyBasic(basicStateView.copy(isLoading = false, throwable = throwable))

}

@Parcelize
data class BasicStateView(
    internal val isLoading: Boolean = false,
    internal val throwable: Throwable? = null
) : Parcelable