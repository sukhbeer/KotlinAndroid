package com.example.android.recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val list: ArrayList<String> = ArrayList()
    private lateinit var manager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.add("Java")
        list.add("Kotlin")
        list.add("Dart")

        manager = LinearLayoutManager(this)

        recyclerView.layoutManager = manager
        recyclerView.adapter = Adapter(this,list)
    }
}
