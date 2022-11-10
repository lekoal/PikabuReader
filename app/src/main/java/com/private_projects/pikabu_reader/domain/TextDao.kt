package com.private_projects.pikabu_reader.domain

import androidx.room.Dao
import androidx.room.Insert
import com.private_projects.pikabu_reader.data.entities.TextBlockEntity

@Dao
interface TextDao {
    @Insert
    fun insert(textBlockEntity: TextBlockEntity)
}