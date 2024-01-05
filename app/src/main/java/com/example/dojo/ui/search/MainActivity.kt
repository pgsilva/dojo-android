package com.example.dojo.ui.search

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
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
        initObservables()
    }

    private fun initComponents() {
        supportActionBar?.hide()
        configureSearchPage()
        configureFloatButton()
        configureSearchInput()
    }

    private fun initObservables() {
        lifecycleScope.launch {
            viewModel.todosList.collect {
                adapter.refresh(it)
            }
        }
    }

    private fun initDependencies() {
        adapter = SearchListAdapter(
            this,
            onDetailSelect = {
                it?.let { configureDetailAction(it) }
            },
            onDoneSelect = {
                it?.let { configureDoneButton(it) }
            })
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

    private fun configureDoneButton(item: Task) {
        Toast.makeText(this, "Parabéns, você é top!", Toast.LENGTH_LONG).show()
        viewModel.done(item.id)
    }

    private fun configureSearchInput() {
        binding.svSearchInput.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrBlank())
                    initObservables()
                else
                    adapter.filter.filter(query)

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank())
                    initObservables()
                else
                    adapter.filter.filter(newText)

                return false
            }
        })
    }

}

