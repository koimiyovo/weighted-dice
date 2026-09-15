package com.kyovo.weighteddice.domain.ports.primary

import com.kyovo.weighteddice.domain.model.Face
import com.kyovo.weighteddice.domain.model.WeightedDice

/**
 * Manages a continuous rolling session: repeatedly rolling a dice in the
 * background until explicitly stopped. Stateful: a session has a
 * lifecycle (started, running, stopped), unlike a single Roller.roll().
 */
interface ContinuousRoller {
    /**
     * Starts rolling [weightedDice] repeatedly in the background (with a
     * pause of [delayMillis] between each roll), calling [onRoll] after
     * every single roll so the caller can display intermediate results.
     *
     * Returns immediately; the rolling happens on a background thread
     * managed internally. Call [stopRolling] to stop it.
     *
     * Throws if a roll is already in progress.
     */
    fun startRolling(weightedDice: WeightedDice, delayMillis: Long, onRoll: (Face) -> Unit)

    /**
     * Stops the currently running roll started by [startRolling], waits
     * for it to actually finish, and returns the last face that was
     * rolled.
     *
     * Throws if no roll is currently in progress.
     */
    fun stopRolling(): Face
}