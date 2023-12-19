package it.macgood.mathanapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedTask(
    @PrimaryKey val id: String,
    val questionNumber: String? = "",
    var questionText: String? = "Вопрос без текста",
    val formula: String? = ""
)
