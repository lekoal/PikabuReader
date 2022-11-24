package com.private_projects.pikabu_reader.ui.hot

import androidx.lifecycle.MutableLiveData
import com.private_projects.pikabu_reader.data.entities.CommonPostEntity
import com.private_projects.pikabu_reader.domain.CommonDatabaseHelper
import com.private_projects.pikabu_reader.domain.ElementsReceiver
import com.private_projects.pikabu_reader.domain.HOT
import com.private_projects.pikabu_reader.ui.ViewModelContract
import com.private_projects.pikabu_reader.utils.ElementToEntityConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.jsoup.nodes.Element

class HotViewModel(
    private val elementsReceiver: ElementsReceiver,
    private val databaseHelper: CommonDatabaseHelper,
    private val converter: ElementToEntityConverter
) : ViewModelContract(elementsReceiver, databaseHelper, converter) {
    override val hotPosts = MutableLiveData<List<CommonPostEntity>>()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun receiveData(page: Int) {
        coroutineScope.launch {
            elementsReceiver.get(HOT, page).collect { elements ->
                convertData(elements)
            }
        }
    }

    private fun convertData(elements: List<Element>) {
        coroutineScope.launch {
            converter.insertRawData(elements).collect { resultList ->
                resultList.forEach { commonPostEntity ->
                    databaseHelper.insertPartialPost(commonPostEntity.postEntity)
                    commonPostEntity.texts?.forEach { text ->
                        databaseHelper.insertText(text)
                    }
                    commonPostEntity.images?.forEach { image ->
                        databaseHelper.insertImage(image)
                    }
                    commonPostEntity.videos?.forEach { video ->
                        databaseHelper.insertVideo(video)
                    }
                }
            }
        }
    }

    override fun readDataFromDB(limit: Int) {
        coroutineScope.launch {
            databaseHelper.getFullPosts(limit)
        }
    }

    override fun onCleared() {
        coroutineScope.cancel()
        super.onCleared()
    }
}