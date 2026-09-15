package com.kyovo.weighteddice

import com.kyovo.weighteddice.application.service.RollService
import com.kyovo.weighteddice.domain.model.*
import com.kyovo.weighteddice.domain.ports.primary.Roller
import com.kyovo.weighteddice.infrastructure.adapters.DefaultRollGenerator

fun main() {
    val probabilities = Faces(
        face1 = Face(number = FaceNumber(1), probability = Probability(10)),
        face2 = Face(number = FaceNumber(2), probability = Probability(20)),
        face3 = Face(number = FaceNumber(3), probability = Probability(30)),
        face4 = Face(number = FaceNumber(4), probability = Probability(15)),
        face5 = Face(number = FaceNumber(5), probability = Probability(15)),
        face6 = Face(number = FaceNumber(6), probability = Probability(10))
    )

    val weightedDice = WeightedDice(probabilities)

    val rollService: Roller = RollService(DefaultRollGenerator())

    println("Rolled ${rollService.roll(weightedDice).print()}")
}