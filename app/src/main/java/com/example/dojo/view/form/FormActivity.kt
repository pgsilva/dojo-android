package com.example.dojo.view.form

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.dojo.R
import com.example.dojo.dao.SearchItemsProvider
import com.example.dojo.domain.search.SearchItem
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


class FormActivity : Activity() {

    private lateinit var dao: SearchItemsProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        loadDependencies()
        configureAvatarLoader()
        configureUpdateButton()
    }

    private fun loadDependencies() {
        dao = SearchItemsProvider()
    }

    private fun configureUpdateButton() {
        findViewById<Button>(R.id.bt_form_update).let { bt ->
            bt.setOnClickListener {
                val (avatar, fullName, username, description) = loadEditTexts()
                val item = SearchItem(
                    avatar.text.toString(),
                    fullName.text.toString(),
                    username.text.toString(),
                    description.text.toString()
                )

                Log.i("FormActivity","Item Criado: [$item]")

                dao.add(item)

                Snackbar.make(bt, "Dados atualizados com sucesso!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()

                finish()
            }
        }
    }

    private fun loadEditTexts(): List<EditText> {
        val avatar = findViewById<EditText>(R.id.et_avatar_url)
        val fullName = findViewById<EditText>(R.id.et_fullname)
        val username = findViewById<EditText>(R.id.et_username)
        val description = findViewById<EditText>(R.id.et_description)

        return listOf(avatar, fullName, username, description)
    }

    private fun configureAvatarLoader() {
        val inputEditText = findViewById<EditText>(R.id.et_avatar_url)

        inputEditText.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable) {
                    findViewById<ImageView>(R.id.iv_form_avatar).let { iv ->
                        Picasso.get().load(s.toString()).into(iv)
                    }
                }
            })
    }
}
