package com.example.dojo.view.search.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dojo.R
import com.example.dojo.domain.search.SearchItem
import com.squareup.picasso.Picasso

class SearchListAdapter(
    private val context: Context,
    private val items: List<SearchItem>
) : RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: SearchItem) {
            val avatar = itemView.findViewById<ImageView>(R.id.iv_avatar)
            val fullName = itemView.findViewById<TextView>(R.id.tv_full_name)
            val username = itemView.findViewById<TextView>(R.id.tv_username)
            val description = itemView.findViewById<TextView>(R.id.tv_description)

            Picasso.get().load(item.avatarUrl).into(avatar)
            fullName.text = item.fullName
            username.text = item.username
            description.text = item.description

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(
                R.layout.item_search_result,
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
       holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
