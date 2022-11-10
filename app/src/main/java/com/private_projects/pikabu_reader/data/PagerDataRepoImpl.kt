package com.private_projects.pikabu_reader.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.private_projects.pikabu_reader.data.entities.CommonPostEntity
import com.private_projects.pikabu_reader.domain.CommonDatabaseHelper
import com.private_projects.pikabu_reader.domain.PagerDataRepo

//class PagerDataRepoImpl(
//    private val commonDatabaseHelper: CommonDatabaseHelper
//) : PagerDataRepo {
//
//    companion object {
//        private const val PAGE_SIZE = 8
//    }
//
//    override fun getPosts(): LiveData<PagingData<CommonPostEntity>> {
//        return Pager(
//            config = PagingConfig(
//
//            ),
//            pagingSourceFactory = {
//
//            },
//            initialKey = 1
//        ).liveData
//    }
//}