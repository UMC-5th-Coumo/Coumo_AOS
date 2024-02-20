package com.umc.coumo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.umc.coumo.databinding.ItemPostBinding
import com.umc.coumo.domain.model.PostModel
import com.umc.coumo.utils.ItemDiffCallback

class PostAdapter(): ListAdapter<PostModel, RecyclerView.ViewHolder>(
    ItemDiffCallback<PostModel>(
        onContentsTheSame = {old, new -> old == new},
        onItemsTheSame = {old, new -> old.id == new.id}
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PostViewHolder(ItemPostBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is PostViewHolder -> {
                holder.bind(item)
            }
        }
    }

    inner class PostViewHolder(
        private val binding: ItemPostBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostModel) {
            binding.item = item

            itemView.setOnClickListener {
                listener?.onItemClick(item)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: PostModel)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}