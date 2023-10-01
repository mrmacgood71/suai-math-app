package it.macgood.mathanapp.domain.model


data class Exercise(
    val id: String,
    val questionNumber: String,
    var questionText: String? = "Вопрос без текста",
    val formula: String? = ""
)