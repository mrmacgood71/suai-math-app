package it.macgood.mathanapp.data.repository

import it.macgood.mathanapp.data.datasource.TaskDao
import it.macgood.mathanapp.data.datasource.entity.toExercise
import it.macgood.mathanapp.domain.model.Exercise
import it.macgood.mathanapp.domain.repository.ExerciseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val dao: TaskDao
): ExerciseRepository {

    override suspend fun getExercises(startId: Int, endId: Int) = flow {
        val value = dao.getExercises()
            .map { it.toExercise() }
            .sortedBy { it.id.toInt() }
        emit(value)
    }.flowOn(Dispatchers.IO)

    override suspend fun getExercise(id: String): Exercise {
        return dao.getExercise(id.toLong()).toExercise()
    }
}