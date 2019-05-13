package com.davidmihola.prismtest

import arrow.core.left
import arrow.core.right
import arrow.optics.Prism
import arrow.optics.modify
import java.io.Serializable

sealed class PlayerViewState : Serializable {
    abstract val uiVisible: Boolean
}

sealed class PlayerViewStateUiVisibleCanChange : PlayerViewState()
sealed class PlayerViewStateUiVisibleCannotChange(override val uiVisible: Boolean) : PlayerViewState()

data class Fullscreen(override val uiVisible: Boolean = true) : PlayerViewStateUiVisibleCanChange()
data class Portrait(override val uiVisible: Boolean = true) : PlayerViewStateUiVisibleCanChange()
object MiniPlayer : PlayerViewStateUiVisibleCannotChange(false)
object Pip : PlayerViewStateUiVisibleCannotChange(false)
object Chromecast : PlayerViewStateUiVisibleCannotChange(false)
object Closed : PlayerViewStateUiVisibleCannotChange(false)

val withUiVisibleFlagPrism: Prism<PlayerViewState, PlayerViewStateUiVisibleCanChange> = Prism(
    getOrModify = {
        when (it) {
            is PlayerViewStateUiVisibleCanChange -> it.right()
            else -> it.left()
        }
    },
    reverseGet = { it }
)

fun PlayerViewState.updateUiVisible(uiVisible: Boolean): PlayerViewState = withUiVisibleFlagPrism.modify(this) {
    when (it) {
        is Fullscreen -> it.copy(uiVisible = uiVisible)
        is Portrait -> it.copy(uiVisible = uiVisible)
    }
}

fun PlayerViewState.toggleUiVisible(): PlayerViewState = withUiVisibleFlagPrism.modify(this) {
    when (it) {
        is Fullscreen -> it.copy(uiVisible = it.uiVisible.not())
        is Portrait -> it.copy(uiVisible = it.uiVisible.not())
    }
}
