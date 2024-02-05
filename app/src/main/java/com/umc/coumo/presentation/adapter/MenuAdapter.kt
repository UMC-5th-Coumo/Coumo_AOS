package com.umc.coumo.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.umc.coumo.databinding.ItemMenuBinding
import com.umc.coumo.databinding.ItemMenuDetailBinding
import com.umc.coumo.domain.model.MenuModel
import com.umc.coumo.utils.ItemDiffCallback

class MenuAdapter(
    private val type: Int
): ListAdapter<MenuModel, RecyclerView.ViewHolder>(
    ItemDiffCallback<MenuModel>(
        onContentsTheSame = {old, new -> old == new},
        onItemsTheSame = {old, new -> old.name == new.name}
    )
) {
companion object {
    const val MENU_ITEM = 0
    const val MENU_DETAIL_ITEM = 1
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (type) {
            MENU_ITEM -> {
                MenuViewHolder(ItemMenuBinding.inflate(inflater, parent,false))
            }
            MENU_DETAIL_ITEM -> {
                MenuDetailViewHolder(ItemMenuDetailBinding.inflate(inflater, parent,false))
            }
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is MenuViewHolder -> {
                holder.bind(item)
            }
            is MenuDetailViewHolder -> {
                holder.bind(item)
            }
        }
    }

    inner class MenuViewHolder(
        private val binding: ItemMenuBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuModel) {
            binding.item = item
        }
    }

    inner class MenuDetailViewHolder(
        private val binding: ItemMenuDetailBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuModel) {
            binding.item = item
        }
    }

    interface OnItemClickListener {
        fun onItemClick(id:Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}