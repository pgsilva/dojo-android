package com.example.dojo.view.form

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import com.example.dojo.dao.SearchItemsProvider
import com.example.dojo.databinding.ActivityFormBinding
import com.example.dojo.domain.search.SearchItem
import com.example.dojo.domain.search.isNotValidURL
import com.squareup.picasso.Picasso


class FormActivity : Activity() {

    private val binding by lazy { ActivityFormBinding.inflate(layoutInflater) }

    private lateinit var dao: SearchItemsProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadDependencies()

        setContentView(binding.root)

        configureAvatarLoader()
        configureUpdateButton()
    }

    private fun loadDependencies() {
        dao = SearchItemsProvider()
    }

    private fun configureUpdateButton() {
        binding.btFormUpdate.let { bt ->
            bt.setOnClickListener {
                val (avatar, fullName, username, description) = loadEditTexts()
                val item = SearchItem(
                    avatar.text.toString(),
                    fullName.text.toString(),
                    username.text.toString(),
                    description.text.toString()
                )

                Log.i("FormActivity", "Item Criado: [$item]")

                dao.add(item)

                finish()
            }
        }
    }

    private fun loadEditTexts(): List<EditText> {
        val avatar = binding.etAvatarUrl
        val fullName =binding.etFullname
        val username = binding.etUsername
        val description = binding.etDescription

        return listOf(avatar, fullName, username, description)
    }

    private fun configureAvatarLoader() {
        val inputEditText = binding.etAvatarUrl

        inputEditText.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable) {
                    binding.ivFormAvatar.let { iv ->
                        if (s.toString().isNotValidURL())
                            Picasso.get().load(s.toString()).into(iv)
                    }
                }
            })
    }
}
