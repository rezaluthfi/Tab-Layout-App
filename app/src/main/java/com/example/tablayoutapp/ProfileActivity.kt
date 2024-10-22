package com.example.tablayoutapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tablayoutapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Mengambil data user dari intent
        val name = intent.getStringExtra("name")
        val nim = intent.getStringExtra("nim")


        // Menampilkan data user
        binding.tvUsername.text = name
        binding.tvNim.text = nim



    }
}