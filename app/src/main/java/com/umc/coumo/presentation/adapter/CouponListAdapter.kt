package com.umc.coumo.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.umc.coumo.databinding.ItemCouponBinding
import com.umc.coumo.domain.model.CouponModel
import com.umc.coumo.utils.ItemDiffCallback

class CouponListAdapter(
    private val context: Context
): ListAdapter<CouponModel, RecyclerView.ViewHolder>(
    ItemDiffCallback<CouponModel>(
        onContentsTheSame = {old, new -> old == new},
        onItemsTheSame = {old, new -> old.name == new.name}
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CouponViewHolder(ItemCouponBinding.inflate(inflater, parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is CouponViewHolder -> {
                holder.bind(item)
            }
        }
    }

    inner class CouponViewHolder(
        private val binding: ItemCouponBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CouponModel) {
            binding.item = item

            val stampAdapter = StampAdapter()
            binding.rvStamp.apply {
                adapter = stampAdapter
                layoutManager = GridLayoutManager(context, item.stampMax/2)
            }

            val result = MutableList(item.stampMax) { i -> i < item.stampCount }
            stampAdapter.submitList(result)

            itemView.setOnClickListener {
                listener?.onItemClick(item.name)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(id:String)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}