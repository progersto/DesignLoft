package com.designloft.ui.main

import com.designloft.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileFragment : BaseFragment() {

    companion object {
        public const val TAG = "ProfileFragment"

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }

    private val viewModel by sharedViewModel<MainViewModel>()
}