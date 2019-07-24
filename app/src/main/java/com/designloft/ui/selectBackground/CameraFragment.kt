package com.designloft.ui.selectBackground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.designloft.R
import com.designloft.base.BaseFragment
import com.designloft.ui.main.contacts.ContactsFragment
import kotlinx.android.synthetic.main.view_toolbar.*

class CameraFragment : BaseFragment() {

    companion object {
        const val TAG = "CameraFragment"

        @JvmStatic
        fun newInstance() = CameraFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_btn.visibility = View.VISIBLE
        text_toolbar.visibility = View.GONE
        filter_btn.visibility = View.GONE
        search_btn.visibility = View.GONE
    }
}