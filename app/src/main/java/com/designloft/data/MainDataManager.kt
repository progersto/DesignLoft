package com.designloft.data

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import com.designloft.database.entities.ProductEntity
import com.designloft.models.Category
import com.designloft.models.Product
import com.designloft.models.RoomImage
import com.designloft.repository.ModelRepository
import com.designloft.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.ArrayList

class MainDataManager(private val prefManager: PreferencesManager, private val modelRepository: ModelRepository) {

    val roomsPhoto = MutableLiveData<MutableList<RoomImage>>().apply { value = null }
    val categories = MutableLiveData<MutableList<Category>>().apply { value = null }
    val products = SingleLiveEvent<MutableList<Product>>().apply { value = null }
    val filteredProducts = SingleLiveEvent<MutableList<Product>>().apply { value = null }
    val favorites = MutableLiveData<MutableList<Product>>().apply { value = null }
    val product = SingleLiveEvent<Product>().apply { value = null }
    val isGeneralList = SingleLiveEvent<Boolean>().apply { value = true }
    val currentBackgroundImage = SingleLiveEvent<Drawable>().apply { value = null }

    fun initDB() {
        GlobalScope.launch(Dispatchers.Main) {
            if (!prefManager.isInit()) {
                modelRepository.initDB()
            }
            getAllCategory()
        }
    }

    fun getRoomImagePhoto(){
        val listCategory: ArrayList<RoomImage> = ArrayList()
        listCategory.add(RoomImage("https://anisola-mebel.ru/wp-content/uploads/2019/04/1010-1-600x600.jpg", 2))
        listCategory.add(RoomImage("https://anisola-mebel.ru/wp-content/uploads/2017/06/Modulnaya-kuhnya-Provans-600x600.jpg",2))
        listCategory.add(RoomImage("https://anisola-mebel.ru/wp-content/uploads/2019/07/Modulnaya-stenka-Best-Beton-Belyiy-glyanets-600x600.jpg",1))
        listCategory.add(RoomImage("https://anisola-mebel.ru/wp-content/uploads/2019/06/Modulnaya-stenka-Oslo-600x600.jpg",1))
        listCategory.add(RoomImage("https://anisola-mebel.ru/wp-content/uploads/2019/07/Spalnya-Vesna-600x600.jpg",3))
        listCategory.add(RoomImage("https://anisola-mebel.ru/wp-content/uploads/2019/06/Spalnyiy-garnitur-Rivera-1-600x600.jpg",3))
        listCategory.add(RoomImage("http://newslab.ru/images/2016/oct/25/01_FullSize.jpg",4))
        listCategory.add(RoomImage("http://housesdesign.ru/bundles/sitehouses/blog_covers/crop/big/75/db/72b6404e9ff9067fae71ff022178.jpg",4))
        roomsPhoto.value = listCategory
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