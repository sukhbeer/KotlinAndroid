 package com.example.room

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*


 class MainActivity : AppCompatActivity() {

     private lateinit var mDb:PersonDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDb = PersonDatabase.getInstance(applicationContext)!!

        btnAdd.setOnClickListener {
            val name = edtName.text.toString().trim()
            val age = edtAge.text.toString().trim().toInt()
            val p =Person(null,name, age)
            doAsync {
                mDb.personDao().InsertData(p)
                uiThread {
                    insertMessage()
                }
            }
        }
    }

     private fun insertMessage() {
         Toast.makeText(this,"Data inserted",Toast.LENGTH_SHORT).show()
     }
 }
