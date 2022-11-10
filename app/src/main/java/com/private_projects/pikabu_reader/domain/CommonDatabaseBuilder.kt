package com.private_projects.pikabu_reader.domain

import android.content.Context
import androidx.room.Room

object CommonDatabaseBuilder {
    private var instance: CommonDatabase? = null

    fun getInstance(context: Context): CommonDatabase? {
        if (instance == null) {
            synchronized(CommonDatabase::class) {
                instance = buildPostsDB(context)
            }
        }
        return instance
    }

    private fun buildPostsDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            CommonDatabase::class.java,
            "fullPostsDB"
        ).build()
}