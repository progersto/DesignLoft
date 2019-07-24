package com.designloft.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.designloft.R

abstract class BaseFragment : Fragment() {

    protected val myLifecycleOwner = MyLifecycleOwner()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myLifecycleOwner.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    override fun onResume() {
        super.onResume()
        myLifecycleOwner.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onPause() {
        super.onPause()
        myLifecycleOwner.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        myLifecycleOwner.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    fun popBackStack() {
        activity?.supportFragmentManager?.popBackStack()
    }

    fun clearStack() {
        activity?.supportFragmentManager?.also { fm ->
            for (i in 0 until fm.backStackEntryCount) {
                fm.popBackStack()
            }
        }
    }

    fun showFragment(fragment: BaseFragment, idContainer: Int, tag: String){
        activity!!.supportFragmentManager
                .beginTransaction()
                .replace(idContainer, fragment)
                .addToBackStack(tag)
                .commit()
    }

    fun addFragment(fragment: BaseFragment, idContainer: Int, tag: String){
        activity!!.supportFragmentManager
            .beginTransaction()
            .add(idContainer, fragment)
            .addToBackStack(tag)
            .commit()
    }

    protected fun replaceFragmentInIdContent(fragment: BaseFragment) {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, fragment)
            .addToBackStack(null)
            .commit()
    }

    open fun requestPermissionsResult(requestCode: Int, permissions: Array<out String>, resultCodes: IntArray) {}

    open fun activityResult(requestCode: Int, resultCode: Int, data: Intent?) {}
}

class MyLifecycleOwner : LifecycleOwner {

    private var mLifecycleRegisty = LifecycleRegistry(this)

    override fun getLifecycle() = mLifecycleRegisty
}

