package com.example.contact_practice_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.example.contact_practice_app.database.Category
import com.example.contact_practice_app.database.Contact
import com.example.contact_practice_app.database.ContactDatamanager
import com.example.contact_practice_app.databinding.ActivityEditContactBinding

class EditContact : AppCompatActivity() {
    private val tag = this::class.simpleName
    var contactPosition = POSITION_NOT_SET
    private lateinit var binding: ActivityEditContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditContactBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)

        //setup spinner
        setupSpinner()

        //title variable
        var titl: String? = null
        contactPosition = intent.getIntExtra(CONTACT_POSITION, POSITION_NOT_SET)
        //checking for contact position
        if (contactPosition != POSITION_NOT_SET) {
            val contact = ContactDatamanager.contacts[contactPosition]
            titl = contact.name
            displayContact(contact)
        } else {
            titl = "New Contact"
        }
        title = titl


//        binding.contentEdit.spinnerCategories.setOnItemClickListener { parent, view, position, id ->
//            val categoryPosition = position
//        }

        binding.contentEdit.saveBt.setOnClickListener {
            if (contactPosition != POSITION_NOT_SET) {
                saveContact()
                actionIntent()
            } else {
                if (binding.contentEdit.nameEt.text.isNullOrBlank() && binding.contentEdit.digitsEt.text.isNullOrBlank()) {
                } /*else if(binding.contentEdit.nameEt.text.isNullOrBlank() ){

                    val activityIntent = Intent(this, ContactListActivity::class.java)
                    startActivity(activityIntent)
                }*/else {
                    saveNewContact()
                    actionIntent()
                }
            }
        }

        Log.d(tag, "onCreate")
    }

    private fun setupSpinner() {
        val spinnerAdapter = ArrayAdapter<Category>(
            this,
            android.R.layout.simple_spinner_item,
            ContactDatamanager.contactCategory
        )
        binding.contentEdit.spinnerCategories.adapter = spinnerAdapter
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    private fun displayContact(contact: Contact) {
        val categoryPosition = ContactDatamanager.contactCategory.indexOf(contact.category)
        binding.contentEdit.spinnerCategories.setSelection(categoryPosition)
        binding.contentEdit.nameEt.setText(contact.name)
        binding.contentEdit.digitsEt.setText(contact.digits)
    }

    private fun actionIntent() {
        val activityIntent = Intent(this, ContactListActivity::class.java)
        startActivity(activityIntent)
    }

    private fun saveContact() {
        val contact = ContactDatamanager.contacts[contactPosition]
        contact.category = binding.contentEdit.spinnerCategories.selectedItem as Category
        contact.name = binding.contentEdit.nameEt.text.toString()
        contact.digits = binding.contentEdit.digitsEt.text.toString()
    }

    private fun saveNewContact() {
        val contact = Contact(
            binding.contentEdit.spinnerCategories.selectedItem as Category,
            binding.contentEdit.nameEt.text.toString(),
            binding.contentEdit.digitsEt.text.toString()
        )
        val contacts = mutableListOf(contact)
        ContactDatamanager.initializeContacts(contacts)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onPause() {
        super.onPause()
        if (contactPosition != POSITION_NOT_SET) {
            saveContact()
        }
        Log.d(tag, "onPause")
    }
}