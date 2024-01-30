package com.umc.coumo.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.umc.coumo.databinding.ItemStoreCouponCountBinding
import com.umc.coumo.domain.model.StoreCouponCountModel
import com.umc.coumo.utils.ItemDiffCallback

class StoreCouponCountAdapter(

): ListAdapter<StoreCouponCountModel, RecyclerView.ViewHolder>(
    ItemDiffCallback<StoreCouponCountModel>(
        onContentsTheSame = {old, new -> old == new},
        onItemsTheSame = {old, new -> old.id == new.id}
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StoreCouponCountViewHolder(ItemStoreCouponCountBinding.inflate(inflater, parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is StoreCouponCountViewHolder -> {
                holder.bind(item)
            }
        }
    }

    inner class StoreCouponCountViewHolder(
        private val binding: ItemStoreCouponCountBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StoreCouponCountModel) {
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