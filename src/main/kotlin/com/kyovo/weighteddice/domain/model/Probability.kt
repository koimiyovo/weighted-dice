package com.kyovo.weighteddice.domain.model

@JvmInline
value class Probability(val value: Int) {
    init {
        require(value in 0..100) { "Probability must be between 0 and 100" }

    }
}