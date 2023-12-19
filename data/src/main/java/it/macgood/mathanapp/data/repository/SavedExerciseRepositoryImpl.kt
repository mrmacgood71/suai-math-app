package it.macgood.mathanapp.data.repository

import it.macgood.mathanapp.data.datasource.SavedTaskDao
import it.macgood.mathanapp.data.datasource.entity.toSavedTask
import it.macgood.mathanapp.data.datasource.entity.toSavedTaskDto
import it.macgood.mathanapp.domain.model.SavedTask
import it.macgood.mathanapp.domain.repository.SavedExerciseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SavedExerciseRepositoryImpl(
    val dao: SavedTaskDao
): SavedExerciseRepository {

    override fun getTasks(): Flow<List<SavedTask>> = flow {
        emit(dao.getSavedTasks().map { it.toSavedTask() })
    }.flowOn(Dispatchers.IO)

    override suspend fun getSavedTask(id: Long): SavedTask? = dao.getSavedTask(id)?.toSavedTask()

    override suspend fun insertSavedTask(exercise: SavedTask) = dao.insertSavedTask(exercise.toSavedTaskDto())

    override suspend fun deleteSavedTask(exercise: SavedTask) : Int = dao.deleteExercises(exercise.toSavedTaskDto())
}