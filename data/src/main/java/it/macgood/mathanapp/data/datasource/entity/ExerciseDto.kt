package it.macgood.mathanapp.data.datasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import it.macgood.mathanapp.domain.model.Exercise
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "exercise")
@Serializable
data class ExerciseDto(
    @PrimaryKey
    val id: String,
    @SerialName(value = "question_number")
    val questionNumber: String? = "",
    @SerialName(value = "question_text")
    var questionText: String? = "Вопрос без текста",
    val formula: String? = ""
) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}

fun ExerciseDto.toExercise() = Exercise(id, questionNumber ?: "", questionText, formula)
fun Exercise.toExerciseDto() = ExerciseDto(id, questionNumber, questionText, formula)



