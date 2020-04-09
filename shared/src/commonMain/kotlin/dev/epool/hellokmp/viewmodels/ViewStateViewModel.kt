package dev.epool.hellokmp.viewmodels

import dev.epool.hellokmp.BaseViewModel
import dev.epool.hellokmp.extensions.toFlow
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.launch

abstract class ViewStateViewModel<STATE : BaseStateView<STATE>>(
    @Suppress("WeakerAccess") val initialState: STATE
) : BaseViewModel() {

    private val _viewState = ConflatedBroadcastChannel(initialState)
    val viewState = _viewState.toFlow()

    protected val currentViewState: STATE
        get() = _viewState.value

    fun reduce(reducer: suspend STATE.() -> STATE) {
        scope.launch {
            try {
                _viewState.offer(currentViewState.toLoading(true))
                _viewState.offer(currentViewState.reducer())
                _viewState.offer(currentViewState.toLoading(false))
            } catch (throwable: Throwable) {
                _viewState.offer(currentViewState.toError(throwable))
            }
        }
    }

}