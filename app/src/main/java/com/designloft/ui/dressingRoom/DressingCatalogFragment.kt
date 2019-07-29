package com.designloft.ui.dressingRoom

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.designloft.R
import com.designloft.base.BaseFragment
import com.designloft.models.Category
import com.designloft.models.Product
import com.designloft.ui.main.MainViewModel
import com.designloft.ui.main.categories.products.ProductsListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.fragment_catalog_container.*
import kotlinx.android.synthetic.main.fragment_catalog_container.dressing_back_ground_image
import kotlinx.android.synthetic.main.view_toolbar.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DressingCatalogFragment : BaseFragment() {

    private val viewModel by sharedViewModel<MainViewModel>()
    private lateinit var catalogProductAdapter: CatalogProductAdapter
    private var productListCatalog = ArrayList<Product>()
    private var productListFavorite = ArrayList<Product>()
    private lateinit var imm: InputMethodManager

    companion object {
        const val TAG = "DressingCatalogFragment"

        @JvmStatic
        fun newInstance() = DressingCatalogFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_catalog_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        dressing_back_ground_image.setImageDrawable(viewModel.currentBackgroundImage.value)
        include_toolbar.text_toolbar.visibility = View.GONE
        include_toolbar.back_btn.visibility = View.GONE
        include_toolbar.filter_btn.visibility = View.VISIBLE
        include_toolbar.search_btn.visibility = View.VISIBLE

        setupMainTab()

        viewModel.categories.observe(myLifecycleOwner, Observer { list -> list?.also { setupCategoryTab(it) } })

        val options = RequestOptions()
            .override(150, 150)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .error(R.drawable.no_image)

        catalogProductAdapter = CatalogProductAdapter(options, productsListener)
        products_adapter.adapter = catalogProductAdapter


        viewModel.getProductsByCategoryId(1)
        viewModel.products.observe(viewLifecycleOwner, Observer { list ->
            list?.also {
                productListCatalog.clear()
                productListCatalog.addAll(list)
                catalogProductAdapter.setItems(list)
                viewModel.isGeneralList.value = true
            }
        })
        viewModel.filteredProducts.observe(viewLifecycleOwner, Observer { list ->
            list?.also {
                catalogProductAdapter.setItems(list)
                viewModel.isGeneralList.value = false
            }
        })
    }

    private val productsListener = object : ProductsListener {
        override fun onItemFavorite(product: Product) {
//                viewModel.updateProductFavorite(product)
            Log.d("fff", "gg")
        }

        override fun onItemClick(product: Product) {
//                addFragment(ProductFragment.newInstance(product.id, product.name), R.id.container_category, TAG)
            Log.d("fff", "gg")
        }
    }

    private fun setupMainTab() {
        catalog_tabs.addTab(catalog_tabs.newTab().setText("Каталог"))
        catalog_tabs.addTab(catalog_tabs.newTab().setText("Избранное"))
        catalog_tabs.addTab(catalog_tabs.newTab().setText("Мой декор"))

        val lis = object : OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> viewModel.getProductsByCategoryId(catalog_tabs_categories.selectedTabPosition + 1)
                    1 -> getFavoriteList()
                    2 -> TODO() //мой декор
                }
            }
        }
        catalog_tabs.addOnTabSelectedListener(lis)
    }

    private fun setupCategoryTab(categoryList: MutableList<Category>) {
        for (i in categoryList.indices) {
            val tab = catalog_tabs_categories.newTab()
            tab.text = categoryList[i].name
            catalog_tabs_categories.addTab(tab)
        }

        val lis = object : OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.also {
                    when (catalog_tabs.selectedTabPosition) {
                        0 -> viewModel.getProductsByCategoryId(it.position + 1)
                        1 -> catalogProductAdapter.setItems(sortFavoriteList())
                    }
                }
            }
        }
        catalog_tabs_categories.addOnTabSelectedListener(lis)
    }

    private fun sortFavoriteList(): MutableList<Product> {
        return productListFavorite.filter { product ->
            product.categoryId == catalog_tabs_categories.selectedTabPosition + 1
        }.toMutableList()
    }

    private fun getFavoriteList(){
        viewModel.getFavorites()
        viewModel.favorites.value?.also {
            productListFavorite.clear()
            productListFavorite.addAll(it)

            catalogProductAdapter.setItems(sortFavoriteList())
        }
    }
}