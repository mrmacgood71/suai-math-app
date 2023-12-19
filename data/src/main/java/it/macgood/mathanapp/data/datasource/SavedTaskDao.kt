package it.macgood.mathanapp.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import it.macgood.mathanapp.data.datasource.entity.SavedTaskDto
import it.macgood.mathanapp.domain.model.SavedTask

@Dao
interface SavedTaskDao {

    @Query("SELECT * FROM saved")
    fun getSavedTasks(): List<SavedTaskDto>

    @Query("SELECT * FROM saved where id = :id")
    suspend fun getSavedTask(id: Long): SavedTaskDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedTask(savedTask: SavedTaskDto)

    @Delete
    suspend fun deleteExercises(saved: SavedTaskDto) : Int
}