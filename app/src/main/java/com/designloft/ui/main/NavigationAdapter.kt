package com.designloft.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.designloft.ui.main.categories.CategoriesFragment
import com.designloft.ui.main.contacts.ContactsFragment
import com.designloft.ui.main.profile.ProfileFragment

class NavigationAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    private var contacts: ContactsFragment? = null
    private var categories: CategoriesFragment? = null
    private var profile: ProfileFragment? = null

    init {
        contacts = ContactsFragment.newInstance()
        categories = CategoriesFragment.newInstance()
        profile = ProfileFragment.newInstance()
    }

    override fun getItem(position: Int): Fragment? {
        return when(position){
            0 -> contacts
            1 -> categories
            2 -> profile
            else -> null
        }
    }

    override fun getCount() = 3
}