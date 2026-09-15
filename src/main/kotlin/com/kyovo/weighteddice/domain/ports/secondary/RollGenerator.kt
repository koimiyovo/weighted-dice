package com.kyovo.weighteddice.domain.ports.secondary

import com.kyovo.weighteddice.domain.model.Roll

interface RollGenerator {
    fun next(from: Int, until: Int): Roll
}