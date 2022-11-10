package com.private_projects.pikabu_reader.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.private_projects.pikabu_reader.data.entities.ImageBlockEntity
import com.private_projects.pikabu_reader.data.entities.PostEntity
import com.private_projects.pikabu_reader.data.entities.TextBlockEntity
import com.private_projects.pikabu_reader.data.entities.VideoBlockEntity

@Database(
    entities = [
        PostEntity::class,
        TextBlockEntity::class,
        ImageBlockEntity::class,
        VideoBlockEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class CommonDatabase : RoomDatabase() {
    abstract fun getFullPostDao(): FullPostDao
    abstract fun getPartialPostDao(): PartialPostDao
    abstract fun getTextDao(): TextDao
    abstract fun getImageDao(): ImageDao
    abstract fun getVideoDao(): VideoDao
}