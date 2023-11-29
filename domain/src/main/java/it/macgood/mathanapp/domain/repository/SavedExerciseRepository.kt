package it.macgood.mathanapp.domain.repository

import androidx.lifecycle.LiveData
import it.macgood.mathanapp.domain.model.Exercise
import kotlinx.coroutines.flow.Flow

interface SavedExerciseRepository {

    fun getTasks(): Flow<List<Exercise>>

    suspend fun getTask(id: Long): Exercise?

    suspend fun insertExercise(exercise: Exercise)

    suspend fun deleteExercises(exercise: Exercise) : Int
}