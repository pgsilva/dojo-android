package com.example.dojo.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dojo.R
import com.example.dojo.dao.SearchItemsProvider
import com.example.dojo.view.form.FormActivity
import com.example.dojo.view.search.adapter.SearchListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class MainActivity : Activity() {

    private lateinit var dao: SearchItemsProvider
    private lateinit var adapter: SearchListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Hello Mr Morales", Toast.LENGTH_LONG).show()
        setContentView(R.layout.activity_main)

        loadDependencies()
        configureSearchPage()
        configureFloatButton()
    }

    override fun onResume() {
        super.onResume()
        adapter.refresh(dao.load())
    }

    private fun loadDependencies() {
        dao = SearchItemsProvider()
        adapter = SearchListAdapter(context = this, items = dao.load())
    }


    private fun configureFloatButton() {
        findViewById<FloatingActionButton>(R.id.fb_add_item).let { fb ->
            fb.setOnClickListener {
                Intent(this, FormActivity::class.java).run {
                    startActivity(this)
                }
            }
        }
    }

    private fun configureSearchPage() {
        findViewById<RecyclerView>(R.id.rv_search_items).let { rv ->
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(this)
        }
    }

}

