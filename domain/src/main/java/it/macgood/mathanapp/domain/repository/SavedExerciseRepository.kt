package it.macgood.mathanapp.domain.repository

import it.macgood.mathanapp.domain.model.Exercise
import it.macgood.mathanapp.domain.model.SavedTask
import kotlinx.coroutines.flow.Flow

interface SavedExerciseRepository {

    fun getTasks(): Flow<List<SavedTask>>

    suspend fun getSavedTask(id: Long): SavedTask?

    suspend fun insertSavedTask(exercise: SavedTask)

    suspend fun deleteSavedTask(exercise: SavedTask) : Int
}