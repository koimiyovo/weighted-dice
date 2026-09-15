package com.kyovo.weighteddice.application.service

import com.kyovo.weighteddice.domain.model.Face
import com.kyovo.weighteddice.domain.model.WeightedDice
import com.kyovo.weighteddice.domain.ports.primary.ContinuousRoller
import com.kyovo.weighteddice.domain.ports.primary.Roller
import java.util.concurrent.atomic.AtomicBoolean

class ContinuousRollService(private val roller: Roller) : ContinuousRoller {

    // Guarded by `this`: only one caller may start/stop a session at a time.
    private var rollingThread: Thread? = null

    private val stopRequested = AtomicBoolean(false)

    @Volatile
    private var lastFace: Face? = null

    override fun startRolling(weightedDice: WeightedDice, delayMillis: Long, onRoll: (Face) -> Unit) {
        synchronized(this) {
            check(rollingThread == null) { "A roll is already in progress. Call stopRolling() first." }

            stopRequested.set(false)

            rollingThread = Thread({ runRollingLoop(weightedDice, delayMillis, onRoll) }, "continuous-roll")
                .apply {
                    // Daemon so a forgotten stopRolling() call never prevents the JVM from exiting.
                    isDaemon = true
                    start()
                }
        }
    }

    override fun stopRolling(): Face {
        val thread = synchronized(this) {
            checkNotNull(rollingThread) { "No roll is currently in progress." }
        }

        stopRequested.set(true)
        thread.join()

        synchronized(this) { rollingThread = null }

        return checkNotNull(lastFace)
    }

    private fun runRollingLoop(weightedDice: WeightedDice, delayMillis: Long, onRoll: (Face) -> Unit) {
        performRoll(weightedDice, onRoll)

        while (!stopRequested.get()) {
            try {
                Thread.sleep(delayMillis)
            } catch (interrupted: InterruptedException) {
                Thread.currentThread().interrupt()
                return
            }

            performRoll(weightedDice, onRoll)
        }
    }

    private fun performRoll(weightedDice: WeightedDice, onRoll: (Face) -> Unit) {
        val face = roller.roll(weightedDice)
        lastFace = face
        onRoll(face)
    }
}