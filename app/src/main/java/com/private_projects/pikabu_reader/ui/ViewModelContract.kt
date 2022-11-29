package com.private_projects.pikabu_reader.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.private_projects.pikabu_reader.data.entities.CommonPostEntity
import com.private_projects.pikabu_reader.domain.CommonDatabaseHelper
import com.private_projects.pikabu_reader.domain.ElementsReceiver
import com.private_projects.pikabu_reader.utils.ElementToEntityConverter

abstract class ViewModelContract(
    private val elementsReceiver: ElementsReceiver,
    private val databaseHelper: CommonDatabaseHelper,
    private val converter: ElementToEntityConverter
) : ViewModel() {
    abstract val receivedPosts: LiveData<List<CommonPostEntity>>
    abstract val isLoading: LiveData<Boolean>
    abstract fun receiveData(page: Int)
    abstract fun readDataFromDB()
}