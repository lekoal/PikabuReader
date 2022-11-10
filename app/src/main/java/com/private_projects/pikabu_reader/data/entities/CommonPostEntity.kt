package com.private_projects.pikabu_reader.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class CommonPostEntity(
    @Embedded
    val postEntity: PostEntity,
    @Relation(
        parentColumn = "id",
        entity = TextBlockEntity::class,
        entityColumn = "textOwnerId"
    )
    val texts: List<TextBlockEntity>?,
    @Relation(
        parentColumn = "id",
        entity = ImageBlockEntity::class,
        entityColumn = "imageOwnerId"
    )
    val images: List<ImageBlockEntity>?,
    @Relation(
        parentColumn = "id",
        entity = VideoBlockEntity::class,
        entityColumn = "videoOwnerId"
    )
    val videos: List<VideoBlockEntity>?

)
