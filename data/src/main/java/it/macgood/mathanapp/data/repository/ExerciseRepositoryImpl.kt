package it.macgood.mathanapp.data.repository

import it.macgood.mathanapp.data.MathAnalysisApi
import it.macgood.mathanapp.data.datasource.ExerciseDto
import it.macgood.mathanapp.data.datasource.toExercise
import it.macgood.mathanapp.domain.model.Exercise
import it.macgood.mathanapp.domain.repository.ExerciseRepository
import retrofit2.Response
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val api: MathAnalysisApi
): ExerciseRepository {

    override suspend fun getExercises(startId: Int, endId: Int): List<Exercise> {

        val response = api.getExercises(startId, endId)
        if (response.body() != null && response.isSuccessful) {
            return response.body()!!.map { it.toExercise() }
        }
        return emptyList()
    }

    override suspend fun getExercise(id: String): Exercise {
        return api.getExercise(id).toExercise()
    }
}