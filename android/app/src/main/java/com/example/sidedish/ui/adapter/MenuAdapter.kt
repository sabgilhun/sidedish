package com.example.sidedish.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sidedish.R
import com.example.sidedish.data.Category
import com.example.sidedish.data.Menu
import com.example.sidedish.data.MenuModel
import com.example.sidedish.databinding.ItemHeaderBinding
import com.example.sidedish.databinding.ItemMenuListBinding
import com.example.sidedish.model.MenuListItem
import com.example.sidedish.ui.MenuItemClickListener
import java.lang.IllegalArgumentException
import java.text.DecimalFormat

class MenuAdapter(
    private val onItemClicked: (Int) -> Unit
) : ListAdapter<MenuListItem, RecyclerView.ViewHolder>(MenuDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            HEADER -> HeaderViewHolder(
                ItemHeaderBinding.inflate(inflater, parent, false)
            )
            ITEM -> MenuViewHolder(
                ItemMenuListBinding.inflate(inflater, parent, false),
                onItemClicked
            )
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        when (holder) {
            is HeaderViewHolder -> holder.bind(item as MenuListItem.Category)
            is MenuViewHolder -> holder.bind(item as MenuListItem.Menu)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is MenuListItem.Category -> HEADER
            is MenuListItem.Menu -> ITEM
        }
    }

    class HeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(header: MenuListItem.Category) {
//            binding.tvMenuListLabel.text = header.category
        }
    }

    class MenuViewHolder(
        private val binding: ItemMenuListBinding,
        private val onItemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(menu: MenuListItem.Menu) {
//            with(binding) {
//                bindData = menu
//                menu.discountPolicy?.let { setBadge(it) }
//            }
//
//            itemView.setOnClickListener {
//                menu.id?.let { key -> onItemClicked(key) }
//            }
        }

        private fun setSalePrice(rate: Int?, price: Int?): Int {
            val discountRate = (rate?.div(100)) ?: 0
            return price?.times(discountRate) ?: 0
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private fun setBadge(sale: String) {
            when (sale) {
                "런칭특가" -> {
                    with(binding) {
                        tvLaunchingCostBadge.visibility = View.VISIBLE
                        tvLaunchingCostBadge.text = sale
                        tvLaunchingCostBadge.background =
                            root.context.getDrawable(R.drawable.background_badge_event)
                    }
                }
                "이벤트특가" -> {
                    with(binding) {
                        tvLaunchingCostBadge.visibility = View.VISIBLE
                        tvLaunchingCostBadge.text = sale
                        tvLaunchingCostBadge.background =
                            root.context.getDrawable(R.drawable.background_badge_limited)
                        tvLaunchingCostBadge.setTextColor(Color.WHITE)
                    }
                }
                else -> {
                    Log.e("Adapter", "setBadge none")
                }
            }
        }
    }

    private object MenuDiffUtil : DiffUtil.ItemCallback<MenuListItem>() {

        override fun areItemsTheSame(
            oldItem: MenuListItem,
            newItem: MenuListItem
        ): Boolean = oldItem.hashCode() == newItem.hashCode()

        override fun areContentsTheSame(
            oldItem: MenuListItem,
            newItem: MenuListItem
        ): Boolean = oldItem == newItem
    }

    companion object {
        private const val HEADER = 0
        private const val ITEM = 1
    }
}