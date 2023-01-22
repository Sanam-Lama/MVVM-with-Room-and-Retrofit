package com.example.nycschools

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NewsActivity : AppCompatActivity() {

    lateinit var description: TextView

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        description = findViewById(R.id.description)

        val article = intent.extras?.getString("DESCRIPTION")
        if (article != null) {
            description.text = article
        }
    }
}