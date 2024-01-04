package com.example.dojo.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dojo.databinding.ItemSearchResultBinding
import com.example.dojo.core.Task
import com.example.dojo.ui.load

class SearchListAdapter(
    private val context: Context,
    items: List<Task>,
    private val onSelect: (Task?) -> Unit,
) : RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    private val dataset = items.toMutableList()

    inner class ViewHolder(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val avatar = binding.ivAvatar
        private val fullName = binding.tvFullName
        private val username = binding.tvUsername
        private val description = binding.tvDescription

        fun bind(item: Task, onSelect: (Task?) -> Unit) {
            avatar.load(item.coverImageUrl)
            fullName.text = item.name
            username.text = item.label
            description.text = item.description
            description.text = item.description

            binding.root.setOnClickListener {
                onSelect(item)
            }
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
    ) = holder.bind(dataset[position], onSelect)

    override fun getItemCount(): Int = dataset.size

    fun refresh(items: List<Task>) {
        dataset.clear()
        dataset.addAll(items)

        notifyItemInserted(items.size)
    }

}


