package com.umc.coumo.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.umc.coumo.databinding.ItemStoreInfoBinding
import com.umc.coumo.domain.model.StoreInfoItemModel
import com.umc.coumo.utils.ItemDiffCallback

class StoreInfoAdapter(

): ListAdapter<StoreInfoItemModel, RecyclerView.ViewHolder>(
    ItemDiffCallback<StoreInfoItemModel>(
        onContentsTheSame = {old, new -> old == new},
        onItemsTheSame = {old, new -> old.id == new.id}
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StoreInfoViewHolder(ItemStoreInfoBinding.inflate(inflater, parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is StoreInfoViewHolder -> {
                holder.bind(item)
            }
        }
    }

    inner class StoreInfoViewHolder(
        private val binding: ItemStoreInfoBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StoreInfoItemModel) {
            binding.item = item

            itemView.setOnClickListener {
                listener?.onItemClick(item.id)
            }
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