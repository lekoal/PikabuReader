package com.private_projects.pikabu_reader.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TextBlockEntity(
    @PrimaryKey(autoGenerate = true)
    val textBlockId: Long = 0,
    val ownerId: String,
    val position: Int,
    val text: String
)
