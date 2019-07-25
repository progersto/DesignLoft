package com.designloft.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.designloft.R
import com.designloft.base.BaseFragment
import com.designloft.ui.main.profile.favorite.FavoriteFragment
import com.designloft.ui.main.profile.myDesign.MyDesignFragment
import com.designloft.utils.FragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.view_toolbar.*

class ProfileFragment : BaseFragment() {

    companion object {
        const val TAG = "ProfileFragment"

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
        back_btn.visibility = View.GONE
        filter_btn.visibility = View.GONE
        search_btn.visibility = View.GONE
    }

    private fun setupViewPager() {
        val adapter = FragmentPagerAdapter(fragmentManager!!)

        val favoriteFragment=  FavoriteFragment.newInstance()
        val myDesignFragment=  MyDesignFragment.newInstance()

        adapter.addFragment(myDesignFragment, resources.getString(R.string.my_design))
        adapter.addFragment(favoriteFragment, resources.getString(R.string.favorite))

        viewpager.adapter = adapter
        tabs.setupWithViewPager(viewpager)
    }
}