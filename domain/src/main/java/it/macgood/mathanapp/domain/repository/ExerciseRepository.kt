package it.macgood.mathanapp.domain.repository

import it.macgood.mathanapp.domain.model.Exercise
import retrofit2.Response
import javax.inject.Singleton

@Singleton
interface ExerciseRepository {

    suspend fun getExercises(startId: Int, endId: Int): List<Exercise>

    suspend fun getExercise(id: String): Exercise
}