package com.private_projects.pikabu_reader.data

import com.private_projects.pikabu_reader.data.entities.*
import com.private_projects.pikabu_reader.domain.CommonDatabase
import com.private_projects.pikabu_reader.domain.CommonDatabaseHelper

class CommonDatabaseHelperImpl(private val commonDatabase: CommonDatabase): CommonDatabaseHelper {
    override suspend fun getFullPosts(size: Int): List<CommonPostEntity> {
        return commonDatabase.getFullPostDao().getFullPost(size)
    }

    override suspend fun insertPartialPost(data: PostEntity) {
        commonDatabase.getPartialPostDao().insert(data)
    }

    override suspend fun insertText(text: TextBlockEntity) {
        commonDatabase.getTextDao().insert(text)
    }

    override suspend fun insertImage(image: ImageBlockEntity) {
        commonDatabase.getImageDao().insert(image)
    }

    override suspend fun insertVideo(video: VideoBlockEntity) {
        commonDatabase.getVideoDao().insert(video)
    }

    override suspend fun clearDB() {
        commonDatabase.getPartialPostDao().deleteAll()
        commonDatabase.getTextDao().deleteAll()
        commonDatabase.getImageDao().deleteAll()
        commonDatabase.getVideoDao().deleteAll()
    }
}