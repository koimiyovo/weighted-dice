package com.kyovo.weigtheddice.model

import kotlin.random.Random

class WeightedDice(val faces: Faces) {
    init {
        val duplicated = faces
            .groupingBy { it.number }
            .eachCount()
            .filter { it.value > 1 }
            .keys

        require(duplicated.isEmpty()) {
            "Every face must have a unique value. Duplicates found: $duplicated."
        }

        val total = faces.sumOf { it.probability.value }
        require(total == 100) {
            "Sum of probabilities must be equal to 100 (currently: $total)."
        }
    }

    fun roll(): Face {
        val roll = Random.nextInt(0, 100)
        var accumulation = 0

        for (face in faces) {
            accumulation += face.probability.value
            if (roll < accumulation) {
                return face
            }
        }

        // This point should never be reached: the sum of probabilities
        // is 100 and the roll is always strictly less than 100,
        // so the last face will always trigger the return above.
        error("Internal error: no face was selected for the roll $roll.")
    }
}