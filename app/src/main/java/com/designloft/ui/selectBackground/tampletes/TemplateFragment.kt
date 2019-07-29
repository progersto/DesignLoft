package com.designloft.ui.selectBackground.tampletes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.designloft.R
import com.designloft.base.BaseFragment
import com.designloft.ui.main.MainViewModel
import com.designloft.utils.FragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_template.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TemplateFragment: BaseFragment() {

    private val viewModel by sharedViewModel<MainViewModel>()

    companion object {
        const val TAG = "TemplateFragment"

        @JvmStatic
        fun newInstance() = TemplateFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_template, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
        back_btn.visibility = View.VISIBLE
        text_toolbar.visibility = View.GONE

        back_btn.setOnClickListener { activity?.onBackPressed() }

        viewModel.getRoomImagePhoto()
    }

    private fun setupViewPager() {
        val adapter = FragmentPagerAdapter(fragmentManager!!)

        adapter.addFragment(AllRoomsFragment.newInstance(0), "Все")
        adapter.addFragment(AllRoomsFragment.newInstance(1), "Гостинная")
        adapter.addFragment(AllRoomsFragment.newInstance(2), "Кухня")
        adapter.addFragment(AllRoomsFragment.newInstance(3), "Спальня")
        adapter.addFragment(AllRoomsFragment.newInstance(4), "Ванная")

        viewpager.adapter = adapter
        tabs.setupWithViewPager(viewpager)
    }
}

