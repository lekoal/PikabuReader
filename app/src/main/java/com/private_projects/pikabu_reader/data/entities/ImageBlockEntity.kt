package com.private_projects.pikabu_reader.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageBlockEntity(
    @PrimaryKey(autoGenerate = true)
    val imageBlockId: Long = 0,
    val ownerId: String,
    val position: Int,
    val url: String
)
