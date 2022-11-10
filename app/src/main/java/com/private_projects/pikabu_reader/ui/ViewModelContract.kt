package com.private_projects.pikabu_reader.ui

import androidx.lifecycle.LiveData
import com.private_projects.pikabu_reader.data.entities.ImageBlockEntity
import com.private_projects.pikabu_reader.data.entities.PostEntity
import com.private_projects.pikabu_reader.data.entities.TextBlockEntity
import com.private_projects.pikabu_reader.data.entities.VideoBlockEntity
import org.jsoup.Connection.Response

interface ViewModelContract {
    val response: LiveData<Response>
    val pikabuPosts: LiveData<List<PostEntity>>
    val textBlocks: LiveData<List<TextBlockEntity>>
    val imageBlocks: LiveData<List<ImageBlockEntity>>
    val videoBlocks: LiveData<List<VideoBlockEntity>>

    fun receiveData(page: Int)
    fun writeDataToDB()
    fun readDataFromDB()
}