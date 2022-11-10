package com.private_projects.pikabu_reader.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.private_projects.pikabu_reader.data.entities.CommonPostEntity

interface PagerDataRepo {
    fun getPosts(): LiveData<PagingData<CommonPostEntity>>
}