package it.macgood.mathanapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey val id: Long,
    val questionNumber: Int?,
    val questionText: String,
    val formula: String?
)
