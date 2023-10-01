package it.macgood.mathanapp.data.datasource

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Query("SELECT * FROM exercise")
    fun getExercises(): LiveData<List<ExerciseDto>>

    @Query("SELECT * FROM exercise where id = :id")
    suspend fun getExercise(id: Long): ExerciseDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: ExerciseDto)

    @Delete
    suspend fun deleteExercises(exercise: ExerciseDto) : Int
}