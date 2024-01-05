package com.example.dojo.ui.search.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.compose.ui.text.toLowerCase
import androidx.recyclerview.widget.RecyclerView
import com.example.dojo.core.Task
import com.example.dojo.databinding.ItemSearchResultBinding
import com.example.dojo.ui.load
import java.util.Locale

class SearchListAdapter(
    private val context: Context,
    private val onDetailSelect: (Task?) -> Unit,
    private val onDoneSelect: (Task?) -> Unit,
) : RecyclerView.Adapter<SearchListAdapter.ViewHolder>(), Filterable {

    private val dataset = mutableListOf<Task>()

    inner class ViewHolder(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val avatar = binding.ivAvatar
        private val fullName = binding.tvFullName
        private val username = binding.tvUsername
        private val description = binding.tvDescription

        fun bind(item: Task, onSelect: (Task?) -> Unit, onDetailSelect: (Task?) -> Unit) {
            avatar.load(item.coverImageUrl)
            fullName.text = item.name
            username.text = item.label
            description.text = item.description
            description.text = item.description

            binding.root.setOnClickListener {
                onSelect(item)
            }

            binding.btListDone.setOnClickListener {
                onDetailSelect(item)
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
    ) = holder.bind(dataset[position], onDetailSelect, onDoneSelect)

    override fun getItemCount(): Int = dataset.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString()
                val filteredList: MutableList<Task> =
                    if (charString.isNullOrBlank()) {
                        dataset
                    } else {
                        dataset.filter {
                            (it.name.lowercase().contains(charString.lowercase())) or
                                    (it.label.lowercase().contains(charString.lowercase()))
                        }.toMutableList()
                    }
                return FilterResults().apply { values = filteredList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if(results != null){
                    val values = results.values as List<Task>
                    if (values.isNotEmpty())
                        refresh(values)
                }
                notifyDataSetChanged()
            }
        }
    }

    fun refresh(items: List<Task>) {
        dataset.clear()
        dataset.addAll(items)

        notifyDataSetChanged()
    }


}


