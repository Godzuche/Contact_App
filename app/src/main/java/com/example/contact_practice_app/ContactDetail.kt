package com.example.contact_practice_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.contact_practice_app.database.ContactDatamanager
import com.example.contact_practice_app.databinding.ActivityContactDetailBinding

class ContactDetail : AppCompatActivity() {
    var contactPosition = POSITION_NOT_SET
    private lateinit var binding: ActivityContactDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)
        contactPosition = intent.getIntExtra(CONTACT_POSITION, POSITION_NOT_SET)

        if (contactPosition != POSITION_NOT_SET) {
            title = "Contact Details"
            displayContactDetails()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                val activityIntent = Intent(this, EditContact::class.java)
                activityIntent.putExtra(CONTACT_POSITION, contactPosition)
                startActivity(activityIntent)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        displayContactDetails()
    }

    private fun displayContactDetails() {
        binding.contentDetails.contactName.text = ContactDatamanager.contacts[contactPosition].name
        binding.contentDetails.contactDigits.text =
            ContactDatamanager.contacts[contactPosition].digits
    }
}