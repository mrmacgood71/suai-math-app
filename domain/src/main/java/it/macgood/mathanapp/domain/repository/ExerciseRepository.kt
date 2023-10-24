package it.macgood.mathanapp.domain.repository

import it.macgood.mathanapp.domain.model.Exercise
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface ExerciseRepository {

    suspend fun getExercises(startId: Int = 0, endId: Int = 0): Flow<List<Exercise>>

    suspend fun getExercise(id: String): Exercise
}