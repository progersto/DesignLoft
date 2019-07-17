package com.designloft.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.designloft.R
import com.designloft.base.BaseFragment
import kotlinx.android.synthetic.main.view_toolbar.*

class ContactsFragment: BaseFragment() {

    companion object {
        const val TAG = "ContactsFragment"

        @JvmStatic
        fun newInstance() = ContactsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_toolbar.text = resources.getString(R.string.contacts_toolbar)
    }

}