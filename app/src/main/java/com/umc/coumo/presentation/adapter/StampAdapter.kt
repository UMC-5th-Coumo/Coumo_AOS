package com.umc.coumo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.umc.coumo.databinding.ItemStampBinding
import com.umc.coumo.utils.ItemDiffCallback

class StampAdapter(): ListAdapter<Boolean, RecyclerView.ViewHolder>(
    ItemDiffCallback<Boolean>(
        onContentsTheSame = {old, new -> old == new},
        onItemsTheSame = {old, new -> old == new}
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StampViewHolder(ItemStampBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is StampViewHolder -> {
                holder.bind(item)
            }
        }
    }

    inner class StampViewHolder(
        private val binding: ItemStampBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Boolean) {
            binding.item = item
        }
    }
}