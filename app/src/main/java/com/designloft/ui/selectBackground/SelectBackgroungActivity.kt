package com.designloft.ui.selectBackground

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.designloft.R
import com.designloft.base.BaseActivity
import kotlinx.android.synthetic.main.activity_select_background.*
import kotlinx.android.synthetic.main.view_select_bg_bottom.*
import kotlinx.android.synthetic.main.view_select_bg_bottom.view.*
import org.jetbrains.anko.textColor

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

    private val idSelected: Int by lazy { intent.getIntExtra(SELECTED_ID, 0) }

    private var navigationAdapter: SelectBgNavigationAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_background)

        navigationAdapter = SelectBgNavigationAdapter(supportFragmentManager, idSelected)
        select_navigation_view_pager.adapter = navigationAdapter
        select_navigation_view_pager.offscreenPageLimit = 2

        select_navigation_view_pager.currentItem = idSelected
        when (idSelected) {
            0 -> select_camera.textColor = ContextCompat.getColor(this, R.color.yellow)
            1 -> select_galery.textColor = ContextCompat.getColor(this, R.color.yellow)
            2 -> select_template.textColor = ContextCompat.getColor(this, R.color.yellow)
        }

        bottom_menu.select_camera.setOnClickListener(myButtonClickListener)
        bottom_menu.select_galery.setOnClickListener(myButtonClickListener)
        bottom_menu.select_template.setOnClickListener(myButtonClickListener)
    }

    private var myButtonClickListener: View.OnClickListener = View.OnClickListener {
        select_camera.textColor = ContextCompat.getColor(this, R.color.white)
        select_galery.textColor = ContextCompat.getColor(this, R.color.white)
        select_template.textColor = ContextCompat.getColor(this, R.color.white)

        when (it.id) {
            R.id.select_camera -> {
                select_navigation_view_pager.currentItem = 0
                select_camera.textColor = ContextCompat.getColor(this, R.color.yellow)
            }
            R.id.select_galery -> {
                select_navigation_view_pager.currentItem = 1
                select_galery.textColor = ContextCompat.getColor(this, R.color.yellow)
            }
            R.id.select_template -> {
                select_navigation_view_pager.currentItem = 2
                select_template.textColor = ContextCompat.getColor(this, R.color.yellow)
            }
        }
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
}