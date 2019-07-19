package com.designloft.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.designloft.database.entities.CategoryEntity
import com.designloft.database.entities.ProductEntity
import com.designloft.models.Category
import com.designloft.models.Product
import com.designloft.repository.ModelRepository
import com.designloft.utils.SingleLiveEvent
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainDataManager(private val preffsManager: PreferencesManager, private val modelRepository: ModelRepository) {

    val categories = MutableLiveData<MutableList<Category>>().apply { value = null }
    val products = MutableLiveData<MutableList<Product>>().apply { value = null }
    val favorites = MutableLiveData<MutableList<Product>>().apply { value = null }
    val product = SingleLiveEvent<Product>().apply { value = null }

    fun initDB() {
        doAsync {
            if (!preffsManager.isInit()) {
                modelRepository.initDB()
            }
            uiThread {
                getAllCategory()
            }
        }
        Log.d("MainDataManager", "init DB = ${!preffsManager.isInit()}")
    }

    fun getProductsByCategoryId(categoryId: Int) {
        products.value = modelRepository.getProductsByCategoryId(categoryId)
    }

    fun getProductByyId(productId: Int) {
        product.value = modelRepository.getProductById(productId)
    }

    fun getAllCategory() {
        categories.value = modelRepository.getAllCategory()
    }

    fun getFavorites() {
        favorites.value = modelRepository.getFavorites()
    }

    fun updateProduct(productEntity: ProductEntity){
        if (modelRepository.updateProduct(productEntity) > 0){
            getProductByyId(productEntity.id!!)
            getProductsByCategoryId(productEntity.categoryId)
            getFavorites()
        }
    }
}