package com.example.dojo.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dojo.core.Task
import com.example.dojo.databinding.ActivityMainBinding
import com.example.dojo.ui.form.FormActivity
import com.example.dojo.ui.search.adapter.SearchListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: SearchListAdapter

    private val viewModel: SearchViewModel by viewModels { SearchViewModel.Factory }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initDependencies()
        initComponents()
    }

    private fun initComponents() {
        supportActionBar?.hide()
        configureSearchPage()
        configureFloatButton()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val content = loadTodos()
            adapter.refresh(content)
        }
    }


    private fun initDependencies() {
        adapter = SearchListAdapter(context = this) { item ->
            item?.let { configureDetailAction(item) }
        }
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

    private fun configureDetailAction(item: Task) {
        Intent(this, FormActivity::class.java).apply {
            putExtra("id", item.id)
        }.run {
            startActivity(this)
        }
    }

    private suspend fun loadTodos() = withContext(Dispatchers.IO) { viewModel.loadTodos() }
}

