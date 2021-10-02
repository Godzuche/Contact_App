package com.example.contact_practice_app

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.contact_practice_app.database.Contact
import com.google.android.material.textview.MaterialTextView

class ContactListRecyclerAdapter(private val context : Context?, private val contacts : List<Contact>) : RecyclerView.Adapter<ContactListRecyclerAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_contact_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.name_tV.text = contact.name
        holder.contactPosition = position
    }

    override fun getItemCount() = contacts.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ico_contact_pic = itemView.findViewById<ImageView>(R.id.icon_contact_pic)
        val name_tV = itemView.findViewById<MaterialTextView>(R.id.name_tV)
        val icons_log = itemView.findViewById<ImageView>(R.id.icons_log)

        var contactPosition = 0

        init {
            itemView.setOnClickListener {
                val intent = Intent(context, ContactDetail::class.java)
                intent.putExtra(CONTACT_POSITION, contactPosition)
                context?.startActivity(intent)
            }
        }
    }
}