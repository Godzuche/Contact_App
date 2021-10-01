package com.example.contact_practice_app.database

data class Contact(var category: Category, var name: String?, var digits: String?)
class Category(val category: String) {
    override fun toString(): String {
        return category
    }
}