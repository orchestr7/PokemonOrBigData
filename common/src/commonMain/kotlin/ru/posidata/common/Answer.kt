package ru.posidata.common

import kotlinx.serialization.Serializable

@Serializable
data class QuestionAndAnswer(
    val answer: ResourceType,
    val question: Resource
)
