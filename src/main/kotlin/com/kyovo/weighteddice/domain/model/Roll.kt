package com.kyovo.weighteddice.domain.model

@JvmInline
value class Roll(val value: Int) {
    init {
        require(value in 0..99) { "Roll value must be between 0 and 99" }
    }
}
