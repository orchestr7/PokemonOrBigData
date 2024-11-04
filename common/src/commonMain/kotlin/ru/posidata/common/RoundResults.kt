package ru.posidata.common

import kotlinx.serialization.Serializable

@Serializable
data class RoundResult(
    val roundNumber: Int,
    val result: Int
)
