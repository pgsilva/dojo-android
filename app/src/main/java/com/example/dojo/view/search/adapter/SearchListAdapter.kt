package com.example.dojo.view.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dojo.databinding.ItemSearchResultBinding
import com.example.dojo.domain.search.SearchItem
import com.example.dojo.view.commons.load

class SearchListAdapter(
    private val context: Context,
    items: List<SearchItem>
) : RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    private val dataset = items.toMutableList()

    class ViewHolder(binding: ItemSearchResultBinding) : RecyclerView.ViewHolder(binding.root) {
        private val avatar = binding.ivAvatar
        private val fullName = binding.tvFullName
        private val username = binding.tvUsername
        private val description = binding.tvDescription

        fun bind(item: SearchItem) {
            avatar.load(item.avatarUrl)
            fullName.text = item.fullName
            username.text = item.username
            description.text = item.description
            description.text = item.description

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ItemSearchResultBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        ).let {
            ViewHolder(it)
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) = holder.bind(dataset[position])

    override fun getItemCount(): Int = dataset.size

    fun refresh(items: List<SearchItem>) {
        dataset.clear()
        dataset.addAll(items)

        notifyItemInserted(dataset.size)
    }
}
