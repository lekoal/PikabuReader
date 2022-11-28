package com.private_projects.pikabu_reader.domain

import com.private_projects.pikabu_reader.data.entities.*

interface CommonDatabaseHelper {
    suspend fun getFullPosts(size: Int): List<CommonPostEntity>
    suspend fun insertPartialPost(data: PostEntity)
    suspend fun insertText(text: TextBlockEntity)
    suspend fun insertImage(image: ImageBlockEntity)
    suspend fun insertVideo(video: VideoBlockEntity)
    suspend fun clearDB()
}