package com.umc.coumo.presentation.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.umc.coumo.databinding.ItemPostImageBinding
import com.umc.coumo.utils.ItemDiffCallback

class PostImageAdapter(): ListAdapter<Uri, RecyclerView.ViewHolder>(
    ItemDiffCallback<Uri>(
        onContentsTheSame = {old, new -> old == new},
        onItemsTheSame = {old, new -> old == new}
    )
){
    inner class ItemViewHolder(
        private val binding: ItemPostImageBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Uri) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(ItemPostImageBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is ItemViewHolder -> {
                holder.bind(item)
            }
        }
    }
}