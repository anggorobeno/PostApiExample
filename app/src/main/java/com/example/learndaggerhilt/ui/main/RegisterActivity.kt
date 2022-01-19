package com.example.learndaggerhilt.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.learndaggerhilt.R
import com.example.learndaggerhilt.data.model.RegisterRequest
import com.example.learndaggerhilt.databinding.ActivityRegisterBinding
import com.example.learndaggerhilt.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        binding.button.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val request = RegisterRequest(username, password, email)
            var isEmptyFields = false
            if (username.isEmpty()) {
                isEmptyFields = true
                binding.etUsername.error = getString(R.string.field_blank)
            }
            if (email.isEmpty()) {
                isEmptyFields = true
                binding.etEmail.error = getString(R.string.field_blank)
            }

            if (password.isEmpty()) {
                isEmptyFields = true
                binding.etPassword.error = getString(R.string.field_blank)
            }
            if (!isEmptyFields) {
            register(request)

        }
        }


        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun register(request: RegisterRequest) {
        viewModel.postRegister(request)
        viewModel.users.observe(this, {
            when (it.status) {
                Status.LOADING -> binding.progressBar.isVisible = true
                Status.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show()
                }
                Status.ERROR -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}