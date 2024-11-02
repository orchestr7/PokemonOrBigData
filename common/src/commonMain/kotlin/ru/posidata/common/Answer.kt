package ru.posidata.common

import kotlinx.serialization.Serializable

@Serializable
data class QuestionAndAnswer(
    val pokemonTypeAnswer: PokemonType,
    val questionId: Int
)
