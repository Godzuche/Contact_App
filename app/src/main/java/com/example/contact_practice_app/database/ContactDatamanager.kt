package com.example.contact_practice_app.database

object ContactDatamanager {
    val contacts = ArrayList<Contact>()
    var contactCategory = ArrayList<Category>()

    init {
        initializeCategories()
    }
    fun initializeCategories() {
        var category = Category("Family")
        contactCategory.add(category)
        category = Category("Friends")
        contactCategory.add(category)
        category = Category("Colleagues")
        contactCategory.add(category)
    }

    fun initializeContacts(contacts: List<Contact>) {
        this.contacts.addAll(contacts)
    }
}