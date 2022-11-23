package com.private_projects.pikabu_reader.ui.hot

import androidx.lifecycle.MutableLiveData
import com.private_projects.pikabu_reader.data.entities.CommonPostEntity
import com.private_projects.pikabu_reader.ui.ViewModelContract

class HotViewModel : ViewModelContract() {

    override val hotPosts = MutableLiveData<List<CommonPostEntity>>()

    override fun receiveData(page: Int) {

    }

    override fun writeDataToDB() {

    }

    override fun readDataFromDB() {

    }
}