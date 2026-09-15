package com.kyovo.weighteddice.infrastructure.adapters

import com.kyovo.weighteddice.domain.model.Roll
import com.kyovo.weighteddice.domain.ports.secondary.RollGenerator
import kotlin.random.Random

class DefaultRollGenerator : RollGenerator {
    override fun next(from: Int, until: Int): Roll {
        val randomValue = Random.nextInt(from, until)
        return Roll(randomValue)
    }
}