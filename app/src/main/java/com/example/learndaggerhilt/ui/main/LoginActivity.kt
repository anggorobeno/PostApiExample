package com.example.learndaggerhilt.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.learndaggerhilt.R
import com.example.learndaggerhilt.data.model.LoginRequest
import com.example.learndaggerhilt.databinding.ActivityLoginBinding
import com.example.learndaggerhilt.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val username = binding.edtUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val request = LoginRequest(username, password)
            var isEmptyFields = false
            if (username.isEmpty()) {
                isEmptyFields = true
                binding.edtUsername.error = getString(R.string.field_blank)
            }

            if (password.isEmpty()) {
                isEmptyFields = true
                binding.etPassword.error = getString(R.string.field_blank)
            }
            if (!isEmptyFields) {
                login(request)
            }

        }
    }

    private fun login(request: LoginRequest) {
        viewModel.postLogin(request)
        viewModel.users.observe(this, {
            when (it.status) {
                Status.ERROR -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                Status.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                Status.LOADING -> binding.progressBar.isVisible = true
            }

        })
    }


}
