package com.umc.coumo.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.umc.coumo.databinding.ItemBannerBinding
import com.umc.coumo.domain.model.BannerCardModel
import com.umc.coumo.utils.ItemDiffCallback

class BannerPagerAdapter(val context: Context) : ListAdapter<BannerCardModel, RecyclerView.ViewHolder>(
    ItemDiffCallback<BannerCardModel>(
        onContentsTheSame = {old, new -> old == new},
        onItemsTheSame = {old, new -> old.id == new.id}
    )
){
    inner class ItemViewHolder(
        private val binding: ItemBannerBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BannerCardModel) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(ItemBannerBinding.inflate(inflater, parent, false))
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