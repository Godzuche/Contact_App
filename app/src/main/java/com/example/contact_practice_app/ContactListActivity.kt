package com.example.contact_practice_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contact_practice_app.database.Contact
import com.example.contact_practice_app.database.ContactDatamanager
import com.example.contact_practice_app.databinding.ActivityMainBinding

class ContactListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val contactListLayoutManager by lazy { LinearLayoutManager(this) }
    private val contactListRecyclerAdapter by lazy { ContactListRecyclerAdapter(this, ContactDatamanager.contacts) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)

        binding.contentMain.lV.layoutManager = contactListLayoutManager
        binding.contentMain.lV.adapter = contactListRecyclerAdapter

        binding.fab.setOnClickListener {
            val activityIntent = Intent(this, EditContact::class.java)
            startActivity(activityIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        (binding.contentMain.lV.adapter)?.notifyDataSetChanged()
    }
}