package com.private_projects.pikabu_reader.ui.hot

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.private_projects.pikabu_reader.data.entities.CommonPostEntity
import com.private_projects.pikabu_reader.databinding.RvHotItemBinding
import kotlin.math.max

class PagingHotAdapter :
    PagingDataAdapter<CommonPostEntity, PagingHotAdapter.HotViewHolder>(PostComparator) {
    companion object {
        val PostComparator = object : DiffUtil.ItemCallback<CommonPostEntity>() {
            override fun areItemsTheSame(
                oldItem: CommonPostEntity,
                newItem: CommonPostEntity
            ): Boolean {
                return oldItem.postEntity.postId == newItem.postEntity.postId
            }

            override fun areContentsTheSame(
                oldItem: CommonPostEntity,
                newItem: CommonPostEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: HotViewHolder, position: Int) {
        val post = getItem(position)
        holder.title.text = post?.postEntity?.title
        holder.author.text = post?.postEntity?.user

        val count = max(max(post?.texts?.size!!, post.images?.size!!), post.videos?.size!!)

        for (i in 0 until count) {
            post.texts.forEach { textBlock ->
                if (textBlock.position == i) {
                    val text = AppCompatTextView(holder.context)
                    text.textSize = 20f
                    text.text = textBlock.text
                    holder.layout.addView(text)
                }
            }
            post.images.forEach { imageBlock ->
                if (imageBlock.position == i) {
                    val image = AppCompatTextView(holder.context)
                    image.textSize = 20f
                    image.text = imageBlock.url
                    holder.layout.addView(image)
                }
            }
            post.videos.forEach { videoBlock ->
                if (videoBlock.position == i) {
                    val video = AppCompatTextView(holder.context)
                    video.textSize = 20f
                    video.text = videoBlock.url
                    holder.layout.addView(video)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvHotItemBinding.inflate(inflater, parent, false)
        return HotViewHolder(binding)
    }

    inner class HotViewHolder(view: RvHotItemBinding) : RecyclerView.ViewHolder(view.root) {
        val layout: LinearLayout = view.rvHotItemLayout
        val title: AppCompatTextView = view.title
        val author: AppCompatTextView = view.author
        val context: Context = view.root.context
    }
}