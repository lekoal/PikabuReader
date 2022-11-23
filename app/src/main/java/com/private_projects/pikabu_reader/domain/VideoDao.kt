package com.private_projects.pikabu_reader.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.private_projects.pikabu_reader.data.entities.VideoBlockEntity

@Dao
interface VideoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(videoBlockEntity: VideoBlockEntity)
}