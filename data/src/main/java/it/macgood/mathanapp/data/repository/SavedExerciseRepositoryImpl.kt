package it.macgood.mathanapp.data.repository

import it.macgood.mathanapp.data.datasource.TaskDao
import it.macgood.mathanapp.data.datasource.toExercise
import it.macgood.mathanapp.data.datasource.toExerciseDto
import it.macgood.mathanapp.domain.model.Exercise
import it.macgood.mathanapp.domain.repository.SavedExerciseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SavedExerciseRepositoryImpl(
    val dao: TaskDao
): SavedExerciseRepository {
    override fun getTasks() = flow {
        emit(dao.getExercises().map { it.toExercise() })
    }.flowOn(Dispatchers.IO)

    override suspend fun getTask(id: Long): Exercise? = dao.getExercise(id)?.toExercise()

    override suspend fun insertExercise(exercise: Exercise) = dao.insertExercise(exercise.toExerciseDto())

    override suspend fun deleteExercises(exercise: Exercise) : Int = dao.deleteExercises(exercise.toExerciseDto())
}