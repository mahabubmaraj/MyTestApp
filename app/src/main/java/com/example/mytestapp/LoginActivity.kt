package com.example.mytestapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEt: EditText
    private lateinit var passEt: EditText
    private lateinit var loginBtn: MaterialButton
    private lateinit var registerTxt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        emailEt = findViewById(R.id.emailEt)
        passEt = findViewById(R.id.passEt)
        loginBtn = findViewById(R.id.loginBtn)
        registerTxt = findViewById(R.id.registerTxt)

        loginBtn.setOnClickListener {
            val email = emailEt.text.toString()
            val pass = passEt.text.toString()
            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "All fields needs to be filled", Toast.LENGTH_SHORT).show()
            } else if (!email.endsWith("@dipti.com.bd")) {
                Toast.makeText(this, "Need a valid email from Dipti", Toast.LENGTH_SHORT).show()
            } else if (!validatePass(pass)) {
                Toast.makeText(
                    this,
                    "Password must be at least 8 characters long and contain Uppercase, Lowercase, Digit and Special character",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        registerTxt.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun validatePass(pass: String): Boolean {
        val upperCasePattern = Regex("[A-Z]")
        val lowerCasePattern = Regex("[a-z]")
        val digitPattern = Regex("\\d")
        val specialCharacterPattern = Regex("[^A-Za-z0-9]")

        return pass.length >= 8 &&
                upperCasePattern.containsMatchIn(pass) &&
                lowerCasePattern.containsMatchIn(pass) &&
                digitPattern.containsMatchIn(pass) &&
                specialCharacterPattern.containsMatchIn(pass)
    }
}