package com.example.dojo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Hello Mr Morales", Toast.LENGTH_LONG).show()

        setContentView(R.layout.activity_main)
    }

}