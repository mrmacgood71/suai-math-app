package it.macgood.mathanapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import it.macgood.mathanapp.data.datasource.ExerciseDto
import it.macgood.mathanapp.data.datasource.TaskDao
import it.macgood.mathanapp.data.datasource.toExercise
import it.macgood.mathanapp.data.datasource.toExerciseDto
import it.macgood.mathanapp.domain.model.Exercise
import it.macgood.mathanapp.domain.repository.SavedExerciseRepository

class SavedExerciseRepositoryImpl(
    val dao: TaskDao
): SavedExerciseRepository {
    override fun getTasks(): LiveData<List<Exercise>> = dao.getExercises().map { it.map { it.toExercise() } }

    override suspend fun getTask(id: Long): Exercise? = dao.getExercise(id)?.toExercise()

    override suspend fun insertExercise(exercise: Exercise) = dao.insertExercise(exercise.toExerciseDto())

    override suspend fun deleteExercises(exercise: Exercise) : Int = dao.deleteExercises(exercise.toExerciseDto())
}