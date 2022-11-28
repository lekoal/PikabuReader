package com.private_projects.pikabu_reader.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.private_projects.pikabu_reader.data.entities.CommonPostEntity
import com.private_projects.pikabu_reader.domain.CommonDatabaseHelper
import com.private_projects.pikabu_reader.domain.ElementsReceiver
import com.private_projects.pikabu_reader.domain.PagerDataRepo
import com.private_projects.pikabu_reader.utils.ElementToEntityConverter

abstract class ViewModelContract(
    private val elementsReceiver: ElementsReceiver,
    private val databaseHelper: CommonDatabaseHelper,
    private val converter: ElementToEntityConverter,
    private val pagerDataRepo: PagerDataRepo
) : ViewModel() {
    abstract fun receiveData(page: Int)
    abstract fun readDataFromDB(): LiveData<PagingData<CommonPostEntity>>
}