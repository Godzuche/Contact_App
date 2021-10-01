package com.example.contact_practice_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.contact_practice_app.database.Contact
import com.example.contact_practice_app.database.ContactDatamanager
import com.example.contact_practice_app.databinding.ActivityMainBinding

class ContactListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ContactDatamanager.contacts)
        binding.contentMain.lV.adapter = adapter

        binding.contentMain.lV.setOnItemClickListener { parent, view, position, id ->
            val activityIntent = Intent(this, ContactDetail::class.java)
            activityIntent.putExtra(CONTACT_POSITION, position)
            startActivity(activityIntent)
        }

        binding.fab.setOnClickListener {
            val activityIntent = Intent(this, EditContact::class.java)
            startActivity(activityIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        (binding.contentMain.lV.adapter as ArrayAdapter<Contact>).notifyDataSetChanged()
    }
}