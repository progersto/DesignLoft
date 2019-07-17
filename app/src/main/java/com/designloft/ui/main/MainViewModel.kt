package com.designloft.ui.main

import com.designloft.base.BaseViewModel
import com.designloft.data.MainDataManager

class MainViewModel(private val dataManager: MainDataManager) : BaseViewModel() {
    val categories = dataManager.categories
    val products = dataManager.products
    val showProductFragment = dataManager.showProductFragment


    fun initDB() {
        dataManager.initDB()
    }

    fun getAllProducts(){
        dataManager.getAllProducts()
    }

    fun getAllCategory(){
        dataManager.getAllCategory()
    }
}