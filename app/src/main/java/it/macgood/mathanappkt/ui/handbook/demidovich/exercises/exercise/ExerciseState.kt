package it.macgood.mathanappkt.ui.handbook.demidovich.exercises.exercise

import it.macgood.mathanapp.domain.model.Exercise

data class ExerciseState(
    val isLoading: Boolean = false,
    val exercise: Exercise? = null,
    val error: String = ""
) {
}