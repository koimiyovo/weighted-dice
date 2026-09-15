package com.kyovo.weigtheddice.model

data class Face(val number: FaceNumber, val probability: Probability) {
    fun print(): String {
        return "Face ${number.value} with a probability of ${probability.value}%"
    }
}
