package com.private_projects.pikabu_reader.data.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.private_projects.pikabu_reader.data.entities.CommonPostEntity
import com.private_projects.pikabu_reader.domain.CommonDatabaseHelper

class LocalDataPagingSource(
    private val commonDatabaseHelper: CommonDatabaseHelper
) : PagingSource<Int, CommonPostEntity>() {

    companion object {
        const val INITIAL_PAGE_INDEX = 0
    }

    override fun getRefreshKey(state: PagingState<Int, CommonPostEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommonPostEntity> {
        val position = params.key ?: INITIAL_PAGE_INDEX
        val posts = commonDatabaseHelper.getFullPosts(params.loadSize)
        return LoadResult.Page(
            data = posts,
            prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
            nextKey = if (posts.isEmpty()) null else position + 1
        )
    }
}