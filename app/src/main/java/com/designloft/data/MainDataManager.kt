package com.designloft.data

import android.util.Log
import com.designloft.database.entities.CategoryItem
import com.designloft.database.entities.ProductItem
import com.designloft.repository.ModelRepository
import com.designloft.utils.SingleLiveEvent
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainDataManager(private val preffsManager: PreferencesManager, private val modelRepository: ModelRepository) {

    val categories = SingleLiveEvent<MutableList<CategoryItem>>().apply { value = null }
    val products = SingleLiveEvent<MutableList<ProductItem>>().apply { value = null }
    val showProductFragment = SingleLiveEvent<Boolean>().apply { value = false }


    fun initDB() {
        doAsync {
            if (!preffsManager.isInit()) {
                modelRepository.initDB()
            }
            uiThread {
                getAllProducts()
                getAllCategory()
            }
        }
        Log.d("MainDataManager", "init DB = ${!preffsManager.isInit()}")
    }

    fun getAllProducts() {
        products.value = modelRepository.getAllProducts()
    }

    fun getAllCategory() {
        categories.value = modelRepository.getAllCategory()
    }

}