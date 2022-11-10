package com.private_projects.pikabu_reader.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageBlockEntity(
    val position: Int?,
    val url: String?
) {
    @PrimaryKey(autoGenerate = true)
    var imageId: Long = 0
    var imageOwnerId: Long = 1
}
