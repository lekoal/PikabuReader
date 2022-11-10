package com.private_projects.pikabu_reader.domain

import androidx.room.Dao
import androidx.room.Insert
import com.private_projects.pikabu_reader.data.entities.ImageBlockEntity

@Dao
interface ImageDao {
    @Insert
    fun insert(imageBlockEntity: ImageBlockEntity)
}