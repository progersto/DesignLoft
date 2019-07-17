package com.designloft.ui.main

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import androidx.fragment.app.Fragment
import com.designloft.R
import com.designloft.base.BaseActivity
import com.designloft.ui.main.profile.ProfileFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private var navigationAdapter: NavigationAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.initDB()

        navigationAdapter = NavigationAdapter(supportFragmentManager)
        navigation_view_pager.adapter = navigationAdapter
        navigation_view_pager.offscreenPageLimit = 2

        configureBottomNavigationView()
    }

    // Set bottom navigation buttons
    private fun configureBottomNavigationView() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            // The 3 fragments
            when (it.itemId) {
                R.id.menu_contacts -> navigation_view_pager.currentItem = 0
                R.id.menu_catalog -> navigation_view_pager.currentItem = 1
                R.id.menu_profile -> navigation_view_pager.currentItem = 2
            }
            true
        }
        // Set default fragment selected
        bottom_navigation.selectedItemId = R.id.menu_catalog
    }

    override fun onBackPressed() {
        val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment is ProfileFragment) {
            finish()
        }
        super.onBackPressed()
    }
}