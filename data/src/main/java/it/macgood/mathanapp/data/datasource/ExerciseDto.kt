package it.macgood.mathanapp.data.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey
import it.macgood.mathanapp.domain.model.Exercise

@Entity(tableName = "exercise")
data class ExerciseDto(
    @PrimaryKey
    val id: String,
    val questionNumber: String,
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

fun ExerciseDto.toExercise() = Exercise(id, questionNumber, questionText, formula)
fun Exercise.toExerciseDto() = ExerciseDto(id, questionNumber, questionText, formula)



