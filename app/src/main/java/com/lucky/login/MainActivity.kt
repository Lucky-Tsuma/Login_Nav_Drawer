package com.lucky.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    lateinit var textInputEmail: TextInputLayout
    lateinit var textInputPassword: TextInputLayout
    lateinit var confirmBtn: Button

    private val PASSWORD_PATTERN =
        Pattern.compile("^" +
                "(?=.*[0-9])" +
                "(?=.*[a-zA-Z])" +
                "(?=\\S+$)" +
                ".{6,}" +
                "$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textInputEmail = findViewById(R.id.input_email)
        textInputPassword = findViewById(R.id.input_password)
        confirmBtn = findViewById(R.id.confirm_button)

        confirmBtn.setOnClickListener {
            if (!validateEmail() || !validatePassword()) {
                return@setOnClickListener
            }

            val email = textInputEmail.editText?.text.toString().trim()
            val password = textInputPassword.editText?.text.toString().trim()
            Toast.makeText(applicationContext, "Email: $email\nPassword: $password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateEmail(): Boolean {
        val emailInput = textInputEmail.editText?.text.toString().trim()

        return if(emailInput.isEmpty()) {
            textInputEmail.error = "Failed, cannot be empty"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            textInputEmail.error = "Please Enter a valid email address"
            false
        } else {
            textInputEmail.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val passwordInput = textInputPassword.editText?.text.toString().trim()

        return if(passwordInput.isEmpty()) {
            textInputPassword.error = "Failed, cannot be empty"
            false
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            textInputPassword.error = "Weak Password"
            false
        } else {
            textInputPassword.error = null
            true
        }
    }
}