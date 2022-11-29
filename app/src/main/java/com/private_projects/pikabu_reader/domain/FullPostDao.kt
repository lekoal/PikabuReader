package com.private_projects.pikabu_reader.domain

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.private_projects.pikabu_reader.data.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FullPostDao {
    @Transaction
    @Query("SELECT * FROM posts")
    fun getFullPost(): Flow<List<CommonPostEntity>>
}