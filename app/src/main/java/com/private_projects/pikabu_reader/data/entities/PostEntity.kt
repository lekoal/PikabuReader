package com.private_projects.pikabu_reader.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
class PostEntity(
    val postId: String,
    val title: String,
    val user: String,
    val rating: String,
    val time: String,
    val views: String
) {
    @PrimaryKey
    var id: Long = 0
}
