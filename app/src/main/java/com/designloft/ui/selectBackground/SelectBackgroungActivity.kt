package com.designloft.ui.selectBackground

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.designloft.R
import com.designloft.base.BaseActivity
import com.designloft.base.BaseFragment
import com.designloft.ui.main.MainViewModel
import com.designloft.ui.main.NavigationAdapter
import com.designloft.ui.main.categories.CategoriesFragment
import com.designloft.ui.main.contacts.ContactsFragment
import com.designloft.ui.main.product.ProductFragment
import com.designloft.ui.main.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.select_background_activity.*
import kotlinx.android.synthetic.main.view_select_bg_bottom.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectBackgroungActivity : BaseActivity() {

    companion object {
        private const val SELECTED_ID = "id_selected"

        fun start(activity: Activity, idSelected: Int) {
            val intent = Intent(activity, SelectBackgroungActivity::class.java).apply {
                putExtra(SELECTED_ID, idSelected)
            }
            activity.startActivity(intent)
        }
    }

    private val idSelected: Int by lazy {
        intent.getIntExtra(SELECTED_ID, 0)
    }

    private val viewModel by viewModel<MainViewModel>()
    private var navigationAdapter: SelectBgNavigationAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_background_activity)

//        viewModel.initDB()

        navigationAdapter = SelectBgNavigationAdapter(supportFragmentManager)
        select_navigation_view_pager.adapter = navigationAdapter
        select_navigation_view_pager.offscreenPageLimit = 2

        select_navigation_view_pager.currentItem = idSelected

        bottom_menu.select_camera.setOnClickListener(myButtonClickListener)
        bottom_menu.select_galery.setOnClickListener(myButtonClickListener)
        bottom_menu.select_template.setOnClickListener(myButtonClickListener)
    }

//    override fun onBackPressed() {
//        val navigationFragment: BaseFragment? = navigationAdapter!!.getCurrentFragment()
//
//        if (navigationFragment is ContactsFragment) {
//            finish()
//        }
//        if (navigationFragment is ProfileFragment) {
//            val listFragments = supportFragmentManager.fragments.filter { frag -> frag.isVisible }
//            val fragment = listFragments[listFragments.size - 1]
//            if (fragment is ProductFragment) {
//                super.onBackPressed()
//            } else {
//                finish()
//            }
//        }
//        if (navigationFragment is CategoriesFragment) {
//            super.onBackPressed()
//        }
//    }

    var myButtonClickListener: View.OnClickListener = View.OnClickListener {
        when (it.id) {
            R.id.select_camera -> select_navigation_view_pager.currentItem = 0
            R.id.select_galery -> select_navigation_view_pager.currentItem = 1
            R.id.select_template -> select_navigation_view_pager.currentItem = 2
        }
    }
}