package com.designloft.ui.dressingRoom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Slide
import android.view.Gravity
import com.designloft.R
import com.designloft.base.BaseActivity
import com.designloft.base.BaseFragment
import com.designloft.ui.main.MainViewModel
import com.designloft.ui.main.categories.CategoriesFragment
import com.designloft.ui.main.contacts.ContactsFragment
import com.designloft.ui.main.product.ProductFragment
import com.designloft.ui.main.profile.ProfileFragment
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DressingRoomActivity : BaseActivity() {

    companion object {
        private const val SELECTED_ID = "id_selected"

        fun start(activity: Activity) {
            val intent = Intent(activity, DressingRoomActivity::class.java).apply {
                //                putExtra(SELECTED_ID, idSelected)
            }
            activity.startActivity(intent)
        }
    }

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dressing_room)

        // We are not interested in defining a new Enter Transition. Instead we change default transition duration
        window.enterTransition.duration = 600

        val mainDressingRoomFragment = MainDressingRoomFragment.newInstance()

        val slideTransition = Slide(Gravity.BOTTOM)
        slideTransition.duration = 500

        val changeBoundsTransition = ChangeBounds()
        changeBoundsTransition.duration = 500

        mainDressingRoomFragment.enterTransition = slideTransition
        mainDressingRoomFragment.allowEnterTransitionOverlap = true
        mainDressingRoomFragment.allowReturnTransitionOverlap = true
        mainDressingRoomFragment.sharedElementEnterTransition = changeBoundsTransition

        showFragment(mainDressingRoomFragment, R.id.dressing_room_container, MainDressingRoomFragment.TAG)

        back_btn.setOnClickListener { finish() }
    }

    override fun onBackPressed() {
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
        super.onBackPressed()
    }
}