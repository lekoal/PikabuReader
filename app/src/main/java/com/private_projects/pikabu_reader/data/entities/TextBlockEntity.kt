package com.private_projects.pikabu_reader.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TextBlockEntity(
    val position: Int?,
    val text: String?
) {
    @PrimaryKey(autoGenerate = true)
    var textId: Long = 0
    var textOwnerId: Long = 1
}
