package com.kyovo.weigtheddice.model

data class FaceProbabilities(
    val face1: FaceOne,
    val face2: FaceTwo,
    val face3: FaceThree,
    val face4: FaceFour,
    val face5: FaceFive,
    val face6: FaceSix
) : Iterable<Face> {
    override fun iterator(): Iterator<Face> {
        return listOf(face1, face2, face3, face4, face5, face6).iterator()
    }
}
