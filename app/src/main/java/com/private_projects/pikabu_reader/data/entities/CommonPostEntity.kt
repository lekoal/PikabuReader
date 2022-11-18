package com.private_projects.pikabu_reader.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CommonPostEntity(
    @Embedded
    val postEntity: PostEntity,
    @Relation(
        parentColumn = "postId",
        entity = TextBlockEntity::class,
        entityColumn = "ownerId"
    )
    val texts: List<TextBlockEntity>?,
    @Relation(
        parentColumn = "postId",
        entity = ImageBlockEntity::class,
        entityColumn = "ownerId"
    )
    val images: List<ImageBlockEntity>?,
    @Relation(
        parentColumn = "postId",
        entity = VideoBlockEntity::class,
        entityColumn = "ownerId"
    )
    val videos: List<VideoBlockEntity>?

)
