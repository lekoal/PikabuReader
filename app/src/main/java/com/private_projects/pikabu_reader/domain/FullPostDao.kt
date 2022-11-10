package com.private_projects.pikabu_reader.domain

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.private_projects.pikabu_reader.data.entities.*

@Dao
interface FullPostDao {
    @Transaction
    @Query("SELECT * FROM posts LIMIT :size")
    fun getFullPost(size: Int): List<CommonPostEntity>
}