package com.example.dojo.ui.form

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.dojo.databinding.ActivityFormBinding
import com.example.dojo.core.Task
import com.example.dojo.ui.load


class FormActivity : AppCompatActivity() {

    private val binding by lazy { ActivityFormBinding.inflate(layoutInflater) }

    private val viewModel: FormViewModel by viewModels { FormViewModel.Factory }

    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        verifyDetailAction()

        initComponents()

    }

    private fun verifyDetailAction() {
        val id = intent.extras?.getString("id")
        id?.let {
            this.id = id
            loadValuesDetail()
        }
    }

    private fun loadValuesDetail() {
        val todo = viewModel.load(id!!)
        val (avatar, fullName, username, description) = loadEditTexts()

        avatar.setText(todo.coverImageUrl)
        fullName.setText(todo.name)
        username.setText(todo.label)
        description.setText(todo.description)

        binding.ivFormAvatar.load(todo.coverImageUrl)
    }

    private fun initComponents() {
        supportActionBar?.hide()
        configureAvatarLoader()
        configureUpdateButton()
        configureRemoveButton()
    }

    private fun configureRemoveButton() {
        if (this.id == null) binding.btFormRemove.visibility = GONE
        else binding.btFormRemove.setOnClickListener {
            viewModel.delete(this.id!!)
            finish()
        }
    }

    private fun configureUpdateButton() {
        binding.btFormUpdate.let { bt ->
            bt.setOnClickListener {
                val (avatar, fullName, username, description) = loadEditTexts()
                val item = Task(
                    avatar.text.toString(),
                    fullName.text.toString(),
                    username.text.toString(),
                    description.text.toString()
                )
                viewModel.add(item, id)
                finish()
            }
        }
    }

    private fun loadEditTexts(): List<EditText> {
        val avatar = binding.etAvatarUrl
        val fullName = binding.etFullname
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
                    binding.ivFormAvatar.load(s.toString())
                }
            })
    }
}
