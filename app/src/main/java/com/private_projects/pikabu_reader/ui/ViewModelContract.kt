package com.private_projects.pikabu_reader.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.private_projects.pikabu_reader.data.entities.CommonPostEntity

abstract class ViewModelContract : ViewModel() {
    abstract val hotPosts: LiveData<List<CommonPostEntity>>

    abstract fun receiveData(page: Int)
    abstract fun writeDataToDB()
    abstract fun readDataFromDB()
}