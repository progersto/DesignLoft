package com.designloft.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.designloft.database.entities.CategoryItem
import com.designloft.database.entities.ProductItem
import com.designloft.repository.ModelRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainDataManager(private val preffsManager: PreferencesManager, private val modelRepository: ModelRepository) {

    val categories = MutableLiveData<MutableList<CategoryItem>>().apply { value = null }
    val products = MutableLiveData<MutableList<ProductItem>>().apply { value = null }


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