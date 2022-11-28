package com.private_projects.pikabu_reader.ui.hot

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.private_projects.pikabu_reader.data.entities.CommonPostEntity
import com.private_projects.pikabu_reader.domain.CommonDatabaseHelper
import com.private_projects.pikabu_reader.domain.ElementsReceiver
import com.private_projects.pikabu_reader.domain.HOT
import com.private_projects.pikabu_reader.domain.PagerDataRepo
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
    private val converter: ElementToEntityConverter,
    private val pagerDataRepo: PagerDataRepo
) : ViewModelContract(elementsReceiver, databaseHelper, converter, pagerDataRepo) {

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

    override fun readDataFromDB(): LiveData<PagingData<CommonPostEntity>> {
        return pagerDataRepo.getPosts().cachedIn(coroutineScope)
    }

    override fun onCleared() {
        coroutineScope.cancel()
        super.onCleared()
    }
}