package com.kyovo.weighteddice.application.service

import com.kyovo.weighteddice.domain.model.*
import com.kyovo.weighteddice.domain.ports.secondary.RollGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RollServiceTest {
    @Test
    fun `roll returns face 1 when draw is 0`() {
        val fixedRandom = object : RollGenerator {
            override fun next(from: Int, until: Int): Roll = Roll(0)
        }
        val dice = WeightedDice(
            Faces(
                Face(FaceNumber(1), Probability(100)),
                Face(FaceNumber(2), Probability(0)),
                Face(FaceNumber(3), Probability(0)),
                Face(FaceNumber(4), Probability(0)),
                Face(FaceNumber(5), Probability(0)),
                Face(FaceNumber(6), Probability(0))
            )
        )
        val rollService = RollService(fixedRandom)

        assertThat(rollService.roll(dice)).isEqualTo(Face(FaceNumber(1), Probability(100)))
    }
}