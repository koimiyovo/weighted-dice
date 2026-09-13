package com.kyovo.weigtheddice.model

import kotlin.random.Random

data class WeightedDice(val faceProbabilities: FaceProbabilities) {
    init {
        val total = faceProbabilities.sumOf { it.probability.value }
        require(total == 100) {
            "Sum of probabilities must be equal to 100 (currently: $total)."
        }
    }

    fun roll(): Face {
        val roll = Random.nextInt(0, 100)
        var accumulation = 0

        for (face in faceProbabilities) {
            accumulation += face.probability.value
            if (roll < accumulation) {
                return face
            }
        }

        // Ce point ne devrait jamais être atteint : la somme des probabilités
        // vaut 100 et le tirage est toujours strictement inférieur à 100,
        // donc la dernière face déclenche forcément le retour ci-dessus.
        error("Internal error: no face was selected for the roll $roll.")
    }
}
