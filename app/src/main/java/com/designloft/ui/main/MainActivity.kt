package com.designloft.ui.main

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.MenuItem
import com.designloft.R
import com.designloft.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity  : BaseActivity()  {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.initCategoryList()


        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottom_navigation.selectedItemId = R.id.menu_catalog
    }

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.menu_contacts -> {
                    replaceFragmentWithoutBackstack(ContactsFragment.newInstance())
                    return true
                }
               R.id.menu_catalog -> {
                    replaceFragmentWithoutBackstack(CatalogFragment.newInstance())
                    return true
                }
               R.id.menu_profile -> {
                    replaceFragmentWithoutBackstack(ProfileFragment.newInstance())
                    return true
                }
            }
            return false
        }
    }

}