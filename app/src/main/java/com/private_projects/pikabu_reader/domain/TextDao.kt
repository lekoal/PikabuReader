package com.private_projects.pikabu_reader.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.private_projects.pikabu_reader.data.entities.TextBlockEntity

@Dao
interface TextDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(textBlockEntity: TextBlockEntity)
}