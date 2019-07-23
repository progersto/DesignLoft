package com.designloft.ui.main

import com.designloft.base.BaseViewModel
import com.designloft.data.MainDataManager
import com.designloft.database.entities.ProductEntity
import com.designloft.models.Product

class MainViewModel(private val dataManager: MainDataManager) : BaseViewModel() {
    val categories = dataManager.categories
    val products = dataManager.products
    val filteredProducts = dataManager.filteredProducts
    val product = dataManager.product
    val favorites = dataManager.favorites
    val isGeneralList = dataManager.isGeneralList


    fun initDB() {
        dataManager.initDB()
    }

    fun getProductsByCategoryId(categoryId: Int){
        dataManager.getProductsByCategoryId(categoryId)
    }

    fun getProductByyId(productId: Int){
        dataManager.getProductByyId(productId)
    }

    fun getFavorites(){
        dataManager.getFavorites()
    }

    fun updateProductFavorite(product: Product){
        dataManager.updateProductFavorite(ProductEntity.toProductEntity(product))
    }

    override fun onCreateView() {
        super.onCreateView()

    }
}