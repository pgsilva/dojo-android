package com.example.dojo.view

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dojo.R
import com.example.dojo.domain.search.fixtureSearchItems
import com.example.dojo.view.search.adapter.SearchListAdapter

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toast.makeText(this, "Hello Mr Morales", Toast.LENGTH_LONG).show()

        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.rv_search_items).let { rv ->
            rv.adapter = SearchListAdapter(
                context = this,
                items = fixtureSearchItems()
            )
            rv.layoutManager = LinearLayoutManager(this)
        }


    }

}

