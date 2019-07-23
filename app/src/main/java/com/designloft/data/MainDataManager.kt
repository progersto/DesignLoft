package com.designloft.data

import androidx.lifecycle.MutableLiveData
import com.designloft.database.entities.ProductEntity
import com.designloft.models.Category
import com.designloft.models.Product
import com.designloft.repository.ModelRepository
import com.designloft.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainDataManager(private val prefManager: PreferencesManager, private val modelRepository: ModelRepository) {

    val categories = MutableLiveData<MutableList<Category>>().apply { value = null }
    val products = SingleLiveEvent<MutableList<Product>>().apply { value = null }
    val filteredProducts = SingleLiveEvent<MutableList<Product>>().apply { value = null }
    val favorites = MutableLiveData<MutableList<Product>>().apply { value = null }
    val product = SingleLiveEvent<Product>().apply { value = null }
    val isGeneralList = SingleLiveEvent<Boolean>().apply { value = true }

    fun initDB() {
        GlobalScope.launch(Dispatchers.Main) {
            if (!prefManager.isInit()) {
                modelRepository.initDB()
            }
            getAllCategory()
        }
    }

    fun getProductsByCategoryId(categoryId: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val list = modelRepository.getProductsByCategoryId(categoryId)
            products.value = list
        }
    }

    fun getProductByyId(productId: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val newProduct = modelRepository.getProductById(productId)
            product.value = newProduct
        }
    }

    fun getAllCategory() {
        GlobalScope.launch(Dispatchers.Main) {
            val list = modelRepository.getAllCategory()
            categories.value = list
        }
    }

    fun getFavorites() {
        GlobalScope.launch(Dispatchers.Main) {
            val list = modelRepository.getFavorites()
            favorites.value = list
        }
    }

    fun updateProductFavorite(productEntity: ProductEntity) {
        GlobalScope.launch(Dispatchers.Main) {
            if (modelRepository.updateProduct(productEntity) > 0) {
                getProductByyId(productEntity.id!!)
                getFavorites()
                if (isGeneralList.value!!) {
                    getProductsByCategoryId(productEntity.categoryId)
                } else {
                    val filteredList = filteredProducts.value!!
                    val updateProd = filteredList.filter { product -> productEntity.id == product.id }[0]
                    val newProd = updateProd.copy(favorite = productEntity.favorite)
                    val position = filteredList.indexOf(updateProd)
                    filteredList[position] = newProd
                    filteredProducts.callWithValue(filteredList)
                }
            }
        }
    }
}