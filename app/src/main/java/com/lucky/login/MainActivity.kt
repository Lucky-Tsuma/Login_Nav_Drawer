package com.lucky.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lucky.login.databinding.ActivityMainBinding
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    /*A property we are going to use to create an instance of the ViewBinding class*/

    private val PASSWORD_PATTERN =
        Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-zA-Z])" +
                    "(?=\\S+$)" +
                    ".{6,}" +
                    "$"
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        /*An instance of the ViewBinding class i.e the name of the xml file but PascalCased*/
        val view = binding.root
        setContentView(view)
        /*Makes the root view of the xml file active, presents it to the screen*/

        binding.confirmButton.setOnClickListener {
            /*the names of the resources have been camelCased, confirmButton was confirm_button in
            * the xml file*/
            if (!validateEmail() || !validatePassword()) {
                return@setOnClickListener
            }

            val email = binding.inputEmail.editText?.text.toString().trim()
            val sh = getSharedPreferences("sharedPref", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sh.edit()
            editor.putString("email", email)
            editor.apply()

            Toast.makeText(
                applicationContext,
                "You have been logged in!",
                Toast.LENGTH_SHORT
            ).show()

            val intentHomepage = Intent(applicationContext, Homepage::class.java)
            startActivity(intentHomepage)
        }
    }

    private fun validateEmail(): Boolean {
        val emailInput = binding.inputEmail.editText?.text.toString().trim()

        return if (emailInput.isEmpty()) {
            binding.inputEmail.error = "Failed, cannot be empty"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            binding.inputEmail.error = "Please Enter a valid email address"
            false
        } else {
            binding.inputEmail.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val passwordInput = binding.inputPassword.editText?.text.toString().trim()

        return if (passwordInput.isEmpty()) {
            binding.inputPassword.error = "Failed, cannot be empty"
            false
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            binding.inputPassword.error = "Weak Password"
            false
        } else {
            binding.inputPassword.error = null
            true
        }
    }
}