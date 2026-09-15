package com.kyovo.weighteddice.domain.ports.primary

import com.kyovo.weighteddice.domain.model.Face
import com.kyovo.weighteddice.domain.model.WeightedDice

interface Roller {
    fun roll(weightedDice: WeightedDice): Face
}