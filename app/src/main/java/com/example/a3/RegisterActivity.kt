package com.example.a3

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val name = findViewById<EditText>(R.id.editTextName)
        val email = findViewById<EditText>(R.id.editTextEmail)
        val password = findViewById<EditText>(R.id.editTextPassword)
        val btnRegister = findViewById<Button>(R.id.buttonRegister)

        btnRegister.setOnClickListener {
            val nameInput = name.text.toString()
            val emailInput = email.text.toString()
            val passInput = password.text.toString()

            if (nameInput.isEmpty()) {
                name.error = "Nama tidak boleh kosong"
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                email.error = "Email tidak valid"
                return@setOnClickListener
            }

            if (passInput.isEmpty()) {
                password.error = "Password tidak boleh kosong"
                return@setOnClickListener
            }

            val sharedPref = getSharedPreferences("user_data", MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("name", nameInput)
                putString("email", emailInput)
                putString("password", passInput)
                apply()
            }

            val user = User(nameInput, emailInput, passInput)
            val intent = Intent(this, LandingActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
            finish()
        }
    }
}
