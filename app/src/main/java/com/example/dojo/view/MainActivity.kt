package com.example.dojo.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dojo.dao.SearchItemsProvider
import com.example.dojo.databinding.ActivityMainBinding
import com.example.dojo.view.form.FormActivity
import com.example.dojo.view.search.adapter.SearchListAdapter


class MainActivity : Activity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var dao: SearchItemsProvider
    private lateinit var adapter: SearchListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Hello Mr Morales", Toast.LENGTH_LONG).show()
        loadDependencies()

        setContentView(binding.root)

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
        binding.fbAddItem.let { fb ->
            fb.setOnClickListener {
                Intent(this, FormActivity::class.java).run {
                    startActivity(this)
                }
            }
        }
    }

    private fun configureSearchPage() {
        binding.rvSearchItems.let { rv ->
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(this)
        }
    }
}

