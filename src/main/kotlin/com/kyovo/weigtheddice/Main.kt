package com.kyovo.weigtheddice

import com.kyovo.weigtheddice.model.*

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

    println("Rolled ${weightedDice.roll().print()}")
}