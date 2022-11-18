package com.private_projects.pikabu_reader.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VideoBlockEntity(
    @PrimaryKey(autoGenerate = true)
    val videoBlockId: Long = 0,
    val ownerId: String,
    val position: Int,
    val url: String
)
