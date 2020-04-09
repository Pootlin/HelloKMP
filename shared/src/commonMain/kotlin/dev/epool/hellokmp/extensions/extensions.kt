package dev.epool.hellokmp.extensions

import dev.epool.hellokmp.CommonFlow
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

@OptIn(FlowPreview::class)
internal fun <T> ConflatedBroadcastChannel<T>.toFlow(): CommonFlow<T> = CommonFlow(asFlow())

internal fun <T> Flow<T>.toFlow(): CommonFlow<T> = CommonFlow(this)