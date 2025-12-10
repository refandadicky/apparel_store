package com.example.apparelstore.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.apparelstore.databinding.ActivityLoginBinding
import com.example.apparelstore.presentation.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListener()
        setupForm()
    }

    private fun setClickListener() {
        binding.btnLogin.setOnClickListener {
            inputLogin()
        }
    }

    private fun inputLogin() {
        val email = binding.layoutForm.etEmail.text.toString().trim()
        val password = binding.layoutForm.etPassword.text.toString().trim()
        doLogin(email, password)
    }

    private fun doLogin(
        email: String,
        password: String,
    ) {
        if (email == "test@example.com" && password == "12345678") {
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
            navigateToMain()
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupForm() {
        with(binding.layoutForm) {
            tilPassword.isVisible = true
            tilEmail.isVisible = true
        }
    }

    private fun navigateToMain() {
        startActivity(
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            },
        )
    }
}
