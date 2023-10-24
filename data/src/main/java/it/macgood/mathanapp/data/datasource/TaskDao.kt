package it.macgood.mathanapp.data.datasource

import androidx.room.*

@Dao
interface TaskDao {

    @Query("SELECT * FROM exercise")
    fun getExercises(): List<ExerciseDto>

    @Query("SELECT * FROM exercise where id = :id")
    suspend fun getExercise(id: Long): ExerciseDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: ExerciseDto)

    @Insert
    suspend fun insertAll(exercise: List<ExerciseDto>)

    @Delete
    suspend fun deleteExercises(exercise: ExerciseDto) : Int
}