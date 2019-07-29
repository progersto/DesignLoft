package com.designloft.ui.dressingRoom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.designloft.R
import com.designloft.base.BaseFragment
import com.designloft.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_inventory.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class InventoryFragment : BaseFragment() {
    private val viewModel by sharedViewModel<MainViewModel>()

    companion object {
        const val TAG = "InventoryFragment"

        fun newInstance() = InventoryFragment().apply {
            arguments = Bundle().apply {
                //                    putInt("pos", id)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_inventory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.text_toolbar.visibility = View.GONE

        dressing_back_ground_image.setImageDrawable(viewModel.currentBackgroundImage.value)
    }
}