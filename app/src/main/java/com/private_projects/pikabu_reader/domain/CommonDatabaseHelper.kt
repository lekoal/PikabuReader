package com.private_projects.pikabu_reader.domain

import com.private_projects.pikabu_reader.data.entities.*
import kotlinx.coroutines.flow.Flow

interface CommonDatabaseHelper {
    suspend fun getFullPosts(): Flow<List<CommonPostEntity>>
    suspend fun insertPartialPost(data: PostEntity)
    suspend fun insertText(text: TextBlockEntity)
    suspend fun insertImage(image: ImageBlockEntity)
    suspend fun insertVideo(video: VideoBlockEntity)
    suspend fun clearDB()
}