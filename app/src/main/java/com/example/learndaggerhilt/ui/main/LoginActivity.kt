package com.example.learndaggerhilt.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.learndaggerhilt.R
import com.example.learndaggerhilt.data.model.LoginRequest
import com.example.learndaggerhilt.data.model.RegisterRequest
import com.example.learndaggerhilt.databinding.ActivityLoginBinding
import com.example.learndaggerhilt.databinding.ActivityMainBinding
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
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val request = LoginRequest(username, password)

        binding.button.setOnClickListener {
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
}