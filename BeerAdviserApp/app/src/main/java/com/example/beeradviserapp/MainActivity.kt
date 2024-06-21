package com.example.beeradviserapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val findBeer = findViewById<Button>(R.id.find_beer)
        findBeer.setOnClickListener {
            Log.v("MainActivity", "Button clicked")
            val beerColor = findViewById<Spinner>(R.id.beer_color)
            val color = beerColor.selectedItem.toString()
            val brands = findViewById<TextView>(R.id.brands)
            brands.text = "Beer color is $color"
        }
    }
}