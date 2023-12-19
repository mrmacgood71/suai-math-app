package it.macgood.mathanapp.data.datasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import it.macgood.mathanapp.domain.model.SavedTask
import kotlinx.serialization.Serializable

@Entity(tableName = "saved")
@Serializable
data class SavedTaskDto(
    @PrimaryKey val id: String,
    val questionNumber: String? = "",
    var questionText: String? = "Вопрос без текста",
    val formula: String? = ""
)

fun SavedTaskDto.toSavedTask() = SavedTask(id, questionNumber ?: "", questionText, formula)
fun SavedTask.toSavedTaskDto() = SavedTaskDto(id, questionNumber, questionText, formula)