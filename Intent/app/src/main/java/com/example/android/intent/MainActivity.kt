package com.example.android.intent

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnShow.setOnClickListener{
            Toast.makeText(this,"Button Clicked",Toast.LENGTH_SHORT).show()
        }

        nextPageBtn.setOnClickListener{
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent);
            Toast.makeText(this,"Button Clicked",Toast.LENGTH_SHORT).show()
        }

    }
}
