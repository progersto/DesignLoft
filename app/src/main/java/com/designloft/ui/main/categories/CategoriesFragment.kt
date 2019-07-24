package com.designloft.ui.main.categories

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.designloft.R
import com.designloft.base.BaseFragment
import com.designloft.models.Category
import com.designloft.ui.main.MainViewModel
import com.designloft.ui.main.categories.products.ProductsFragment
import com.designloft.ui.selectBackground.SelectBackgroungActivity
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_select_design.view.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CategoriesFragment : BaseFragment() {

    companion object {
        const val TAG = "CategoriesFragment"
        const val REQUEST_CODE = 12345

        @JvmStatic
        fun newInstance() = CategoriesFragment()
    }

    private val viewModel by sharedViewModel<MainViewModel>()

    private lateinit var categoriesAdapter: CategoriesAdapter
    private var catalogList = ArrayList<Category>()
    private lateinit var viewSelectDesign: View
    private lateinit var dialog: AlertDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_toolbar.text = resources.getString(R.string.app_name)
        back_btn.visibility = View.GONE
        filter_btn.visibility = View.GONE
        search_btn.visibility = View.GONE

        viewSelectDesign = View.inflate(context, R.layout.fragment_select_design, null)
        create_design.setOnClickListener {
            val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            if (hasPermissions(context!!, *permissions)) {
                showSelectedDialog()
            } else {
                requestPermissions((permissions), REQUEST_CODE)
            }
        }
        viewSelectDesign.select_design_galery.setOnClickListener {
            SelectBackgroungActivity.start(activity!!, 1)
        }
        viewSelectDesign.select_design_ar.setOnClickListener {

        }
        viewSelectDesign.select_design_camera.setOnClickListener {
            SelectBackgroungActivity.start(activity!!, 0)
        }
        viewSelectDesign.select_design_template.setOnClickListener {
            SelectBackgroungActivity.start(activity!!, 2)
        }

        val options = RequestOptions()
            .override(200, 200)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .error(R.drawable.no_image)

        categoriesAdapter = CategoriesAdapter(options) { category ->
            showFragment(ProductsFragment.newInstance(category.name, category.id), R.id.container_category, TAG)
        }
        catalog_adapter.adapter = categoriesAdapter

        viewModel.categories.observe(myLifecycleOwner, Observer { list ->
            list?.also {
                catalogList.clear()
                catalogList.addAll(it)
                categoriesAdapter.setItems(it)
            }
        })
    }

    private fun showSelectedDialog() {
        viewSelectDesign.parent?.also { (it as ViewGroup).removeView(viewSelectDesign) }

        dialog = AlertDialog.Builder(activity as Context)
            .setView(viewSelectDesign)
            .setNegativeButton(resources.getString(R.string.select_design_cancel)) { dialog, _ -> dialog.dismiss() }
            .setTitle(resources.getString(R.string.select_design_title))
            .show()
    }

    private fun hasPermissions(context: Context, vararg permissions: String): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, resultCodes: IntArray) {
        if (requestCode == REQUEST_CODE && resultCodes.count { it == PackageManager.PERMISSION_GRANTED } == resultCodes.size) {
            showSelectedDialog()
        } else {
            toast(R.string.select_design_no_permission)
        }
    }

}
