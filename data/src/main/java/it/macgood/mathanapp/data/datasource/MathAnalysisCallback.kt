package it.macgood.mathanapp.data.datasource

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import it.macgood.mathanapp.data.R
import it.macgood.mathanapp.data.datasource.entity.ExerciseDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Provider

class MathAnalysisCallback(
    private val context: Context,
    private val provider: Provider<TaskDao>
) : RoomDatabase.Callback() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch {
            populateCodes()
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private suspend fun populateCodes() {
        val inputStream = context.resources.openRawResource(R.raw.task3)
        val tasksList: List<ExerciseDto> = Json.decodeFromStream(inputStream)
        provider.get().insertAll(tasksList)
    }
}