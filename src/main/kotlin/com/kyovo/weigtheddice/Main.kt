package com.kyovo.weigtheddice

import com.kyovo.weigtheddice.model.*

fun main() {
    val probabilities = FaceProbabilities(
        face1 = FaceOne(probability = Probability(10)),
        face2 = FaceTwo(probability = Probability(20)),
        face3 = FaceThree(probability = Probability(30)),
        face4 = FaceFour(probability = Probability(15)),
        face5 = FaceFive(probability = Probability(15)),
        face6 = FaceSix(probability = Probability(10))
    )

    val weightedDice = WeightedDice(probabilities)

    println("Rolled ${weightedDice.roll()}")
}