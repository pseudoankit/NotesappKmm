package com.pseudoankit.notesappkmm.android.presentation.util

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.flow.MutableSharedFlow

suspend inline fun <EFFECT> MutableSharedFlow<EFFECT>.postSideEffect(block: () -> EFFECT) {
    emit(block())
}

inline fun <STATE> MutableState<STATE>.setState(block: STATE.() -> STATE) {
    value = value.block()
}