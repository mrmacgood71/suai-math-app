package it.macgood.mathanapp.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import it.macgood.mathanapp.data.datasource.entity.ExerciseDto
import it.macgood.mathanapp.data.datasource.entity.SavedTaskDto

@Database(
    entities = [ExerciseDto::class, SavedTaskDto::class],
    version = 1,
    exportSchema = false
)
abstract class MathAnalysisDatabase: RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val savedTaskDao: SavedTaskDao

    companion object {
        const val DATABASE_NAME = "mathanalysis"
    }
}