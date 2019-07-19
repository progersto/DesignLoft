package com.designloft.ui.main.categories.products

import com.designloft.models.Product

interface ProductsListener {

    fun onItemFavorite(product: Product)

    fun onItemClick(product: Product)
}