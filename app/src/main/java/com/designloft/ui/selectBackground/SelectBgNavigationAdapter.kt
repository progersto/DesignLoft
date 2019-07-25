package com.designloft.ui.selectBackground

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.designloft.base.BaseFragment
import com.designloft.ui.selectBackground.tampletes.TemplateFragment

class SelectBgNavigationAdapter(fm: FragmentManager, idSelected: Int): FragmentPagerAdapter(fm){

    private var cameraFragment: CameraFragment? = null
    private var galeryFragment: GalleryFragment? = null
    private var templateFragment: TemplateFragment? = null

    init {
        cameraFragment = CameraFragment.newInstance(idSelected)
        galeryFragment = GalleryFragment.newInstance(idSelected)
        templateFragment = TemplateFragment.newInstance()
    }

    override fun getItem(position: Int): BaseFragment? {
        return when(position){
            0 -> cameraFragment
            1 -> galeryFragment
            2 -> templateFragment
            else -> null
        }
    }

    override fun getCount() = 3

    private var mCurrentFragment: BaseFragment? = null

    fun getCurrentFragment(): BaseFragment? {
        return mCurrentFragment
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        if (getCurrentFragment() !== `object`) {
            mCurrentFragment = `object` as BaseFragment
        }
        super.setPrimaryItem(container, position, `object`)
    }
}