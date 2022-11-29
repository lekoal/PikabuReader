package com.private_projects.pikabu_reader.ui.hot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.private_projects.pikabu_reader.R
import com.private_projects.pikabu_reader.data.entities.CommonPostEntity

class HotRVAdapter : RecyclerView.Adapter<HotRVViewHolder>() {
    private val data: MutableList<CommonPostEntity> = mutableListOf()

    fun setData(posts: List<CommonPostEntity>) {
        data.addAll(posts)
        notifyItemRangeInserted(0, posts.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotRVViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_hot_item, parent, false)
        return HotRVViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotRVViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun getItem(position: Int): CommonPostEntity {
        return data[position]
    }
}