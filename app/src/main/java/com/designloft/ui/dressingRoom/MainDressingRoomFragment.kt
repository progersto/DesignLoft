package com.designloft.ui.dressingRoom

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.designloft.R
import com.designloft.base.BaseFragment
import com.designloft.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_main_dressing_room.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MainDressingRoomFragment : BaseFragment() {
    //    private lateinit var onMenuItemClick: OnMenuItemClick
    private val viewModel by sharedViewModel<MainViewModel>()

    companion object {
        const val TAG = "MainDressingRoomFragment"

        fun newInstance(): MainDressingRoomFragment = MainDressingRoomFragment().apply {
            arguments = Bundle().apply {
                //                    putInt("pos", id)
//                    putString("name", name)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_dressing_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dressing_back_ground_image.setImageDrawable(viewModel.currentBackgroundImage.value)
        activity!!.text_toolbar.visibility = View.GONE

        dressing_inventory.setOnClickListener {
            val inventoryFragment = InventoryFragment.newInstance()

            val slideTransition = Slide(Gravity.BOTTOM)
            slideTransition.duration = 600

            val changeBoundsTransition = ChangeBounds()
            changeBoundsTransition.duration = 600

            inventoryFragment.enterTransition = slideTransition
            inventoryFragment.allowEnterTransitionOverlap = true
            inventoryFragment.allowReturnTransitionOverlap = true
            inventoryFragment.sharedElementEnterTransition = changeBoundsTransition

            replaceFragmentWithSharedElement(inventoryFragment, R.id.dressing_room_container, TAG, dressing_back_ground_image)
        }

        dressing_catalog.setOnClickListener {
            val dressingCatalogFragment = DressingCatalogFragment.newInstance()

            val slideTransition = Slide(Gravity.BOTTOM)
            slideTransition.duration = 600

            val changeBoundsTransition = ChangeBounds()
            changeBoundsTransition.duration = 600

            dressingCatalogFragment.enterTransition = slideTransition
            dressingCatalogFragment.allowEnterTransitionOverlap = true
            dressingCatalogFragment.allowReturnTransitionOverlap = true
            dressingCatalogFragment.sharedElementEnterTransition = changeBoundsTransition

            replaceFragmentWithSharedElement(dressingCatalogFragment, R.id.dressing_room_container, TAG, dressing_back_ground_image)
        }
    }
}