package com.designloft.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.designloft.R
import com.designloft.base.BaseFragment
import com.designloft.database.entities.CategoryItem
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CategoriesFragment : BaseFragment() {

    companion object {
        const val TAG = "CategoriesFragment"

        @JvmStatic
        fun newInstance() = CategoriesFragment()
    }

    private val viewModel by sharedViewModel<MainViewModel>()

    private lateinit var categoriesAdapter: CategoriesAdapter
    private var catalogList = ArrayList<CategoryItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.categories.value?.apply {
            viewModel.getAllCategory()
        }

        text_toolbar.text = resources.getString(R.string.app_name)

        val options = RequestOptions()
            .override(200, 200)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .error(R.drawable.no_image)

        categoriesAdapter = CategoriesAdapter(options) { category ->
            showFragment(ProductsFragment.newInstance(category.name))
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
//        auth_login_btn.setOnClickListener {
//            viewModel.showLogin.call()
//        }
}