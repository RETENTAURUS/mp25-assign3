package com.example.a3

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LandingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        val user = intent.getParcelableExtra<User>("user")
        val welcomeText = findViewById<TextView>(R.id.textWelcome)

        welcomeText.text = "Welcome, ${user?.name}!"
    }
}
