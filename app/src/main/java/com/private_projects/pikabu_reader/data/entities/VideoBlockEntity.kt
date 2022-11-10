package com.private_projects.pikabu_reader.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VideoBlockEntity(
    val position: Int?,
    val url: String?
) {
    @PrimaryKey(autoGenerate = true)
    var videoId: Long = 0
    var videoOwnerId: Long = 1
}
