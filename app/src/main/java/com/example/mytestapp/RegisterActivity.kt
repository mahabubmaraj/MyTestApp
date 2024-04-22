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

class RegisterActivity : AppCompatActivity() {

    private lateinit var usernameEt: EditText
    private lateinit var emailEt: EditText
    private lateinit var passEt: EditText
    private lateinit var conPassEt: EditText
    private lateinit var registerBtn: MaterialButton
    private lateinit var loginTxt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        usernameEt = findViewById(R.id.usernameEt)
        emailEt = findViewById(R.id.emailEt)
        passEt = findViewById(R.id.passEt)
        conPassEt = findViewById(R.id.conPassEt)
        registerBtn = findViewById(R.id.registerBtn)
        loginTxt = findViewById(R.id.loginTxt)

        registerBtn.setOnClickListener {
            val username = usernameEt.text.toString()
            val email = emailEt.text.toString()
            val pass = passEt.text.toString()
            val conPass = conPassEt.text.toString()

            if (username.isEmpty() || email.isEmpty() || pass.isEmpty() || conPass.isEmpty()) {
                Toast.makeText(this, "All fields needs to be filled", Toast.LENGTH_SHORT).show()
            } else if (!email.endsWith("@dipti.com.bd")) {
                Toast.makeText(this, "Need a valid email from Dipti", Toast.LENGTH_SHORT).show()
            } else if (pass != conPass) {
                Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show()
            } else if (!validatePass(pass)) {
                Toast.makeText(
                    this,
                    "Password must be at least 8 characters long and contain Uppercase, Lowercase, Digit and Special character",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        loginTxt.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
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