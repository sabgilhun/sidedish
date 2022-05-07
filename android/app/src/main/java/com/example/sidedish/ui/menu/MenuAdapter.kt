package com.example.sidedish.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sidedish.databinding.ItemHeaderBinding
import com.example.sidedish.databinding.ItemMenuListBinding
import com.example.sidedish.model.MenuListItem

class MenuAdapter(
    private val onItemClicked: (MenuListItem.Menu) -> Unit
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
            binding.tvMenuListLabel.text = header.categoryName
        }
    }

    class MenuViewHolder(
        private val binding: ItemMenuListBinding,
        private val onItemClicked: (MenuListItem.Menu) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(menu: MenuListItem.Menu) {
            binding.item = menu
            itemView.setOnClickListener {
                onItemClicked(menu)
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