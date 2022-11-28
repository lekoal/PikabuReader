package com.private_projects.pikabu_reader.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.private_projects.pikabu_reader.data.entities.ImageBlockEntity

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(imageBlockEntity: ImageBlockEntity)

    @Query("DELETE FROM images")
    fun deleteAll()
}