package com.kyovo.weighteddice.domain.ports.primary

import com.kyovo.weighteddice.domain.model.Face
import com.kyovo.weighteddice.domain.model.WeightedDice

interface Roller {
    /**
     * Rolls the dice once and returns the resulting face.
     */
    fun roll(weightedDice: WeightedDice): Face
}