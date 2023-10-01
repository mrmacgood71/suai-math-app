package it.macgood.mathanapp.domain.usecase

import it.macgood.mathanapp.domain.repository.SavedExerciseRepository


class GetTasks(
    private val repository: SavedExerciseRepository
) {
}