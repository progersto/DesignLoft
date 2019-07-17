package com.designloft.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.designloft.R
import com.designloft.base.BaseFragment
import com.designloft.ui.main.MainViewModel
import com.designloft.ui.main.categories.CategoriesFragment
import com.designloft.ui.main.contacts.ContactsFragment
import com.designloft.ui.main.profile.favorite.FavoriteFragment
import com.designloft.ui.main.profile.myDesign.MyDesignFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileFragment : BaseFragment() {

    companion object {
        public const val TAG = "ProfileFragment"

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }

    private val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
    }

    private fun setupViewPager() {

        val adapter = MyFragmentPagerAdapter(fragmentManager!!)

        val favoriteFragment=  FavoriteFragment.newInstance()
        val myDesignFragment=  MyDesignFragment.newInstance()

        adapter.addFragment(myDesignFragment, resources.getString(R.string.my_design))
        adapter.addFragment(favoriteFragment, resources.getString(R.string.favorite))

        viewpager.adapter = adapter
        tabs.setupWithViewPager(viewpager)
    }
}