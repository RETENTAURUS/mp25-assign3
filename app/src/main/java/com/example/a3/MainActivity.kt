package com.example.a3

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = findViewById<EditText>(R.id.editTextEmail)
        val password = findViewById<EditText>(R.id.editTextPassword)
        val btnLogin = findViewById<Button>(R.id.buttonLogin)
        val btnToRegister = findViewById<TextView>(R.id.textRegister)

        btnLogin.setOnClickListener {
            val emailInput = email.text.toString()
            val passInput = password.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                email.error = "Masukkan email yang valid"
                return@setOnClickListener
            }

            if (passInput.isEmpty()) {
                password.error = "Password tidak boleh kosong"
                return@setOnClickListener
            }

            val sharedPref = getSharedPreferences("user_data", MODE_PRIVATE)
            val savedEmail = sharedPref.getString("email", "")
            val savedPassword = sharedPref.getString("password", "")
            val savedName = sharedPref.getString("name", "")

            if (emailInput == savedEmail && passInput == savedPassword) {
                val user = User(savedName ?: "Guest", emailInput, passInput)
                val intent = Intent(this, LandingActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Email atau Password salah", Toast.LENGTH_SHORT).show()
            }
        }

        btnToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
