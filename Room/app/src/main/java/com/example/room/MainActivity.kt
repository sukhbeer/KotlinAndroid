 package com.example.room

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
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
                mDb.personDao().insertData(p)
                uiThread {
                    insertMessage()
                }
            }
        }

        btnGet.setOnClickListener {
            doAsync {
                val fetch = mDb.personDao().getAll()
                uiThread {
                    populateList(fetch)
                }
            }
        }

    }

     private fun populateList(data: List<Person>) {
         if(data.isEmpty()){
             Toast.makeText(this,"No inserted",Toast.LENGTH_SHORT).show()
             return
         }

         val adapter =object:ArrayAdapter<Person>(this,android.R.layout.simple_list_item_2,android.R.id.text1,data){
             override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                 val v = super.getView(position, convertView, parent)
                 val p = data[position]
                 v.findViewById<TextView>(android.R.id.text1).text = p.name
                 v.findViewById<TextView>(android.R.id.text2).text = p.age.toString()
                 return v
             }
         }

         listData.adapter = adapter

     }


     private fun insertMessage() {
         Toast.makeText(this,"Data inserted",Toast.LENGTH_SHORT).show()
     }
 }
