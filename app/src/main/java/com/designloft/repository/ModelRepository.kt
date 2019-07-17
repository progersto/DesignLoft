package com.designloft.repository

import com.designloft.data.PreferencesManager
import com.designloft.database.daos.CategoriesDao
import com.designloft.database.daos.ProductsDao
import com.designloft.database.entities.CategoryItem
import com.designloft.database.entities.ProductItem
import org.jetbrains.anko.doAsyncResult
import java.util.ArrayList

class ModelRepository(
    private val preffsManager: PreferencesManager,
    private val categoriesDao: CategoriesDao,
    private val productsDao: ProductsDao
) {

//    private var listCategories = mutableListOf<CategoryItem>()
//    private var listProducts = mutableListOf<ProductItem>()

    private fun initCategoryList(): ArrayList<CategoryItem> {
        val listCategory: ArrayList<CategoryItem> = java.util.ArrayList()
        listCategory.add(CategoryItem(null, "Столы", "http://www.vashdom.ru/files/articles/6000/6014/2.jpg"))
        listCategory.add(CategoryItem(null, "Гардероб", "http://rusmebelshop.ru/img/image/divan.jpg"))
        listCategory.add(CategoryItem(null, "Декор", "http://rusmebelshop.ru/img/image/kreslo1.jpg"))
        listCategory.add(CategoryItem(null, "Диваны", "http://rusmebelshop.ru/img/image/kreslo3.jpg"))
        listCategory.add(CategoryItem(null, "Стулья", "https://images.clipartlogo.com/files/istock/previews/1048/104875165-wooden-chairs-on-a-white-background-wooden-furniture.jpg"))
        listCategory.add(CategoryItem(null, "Лампы", "http://v.albinos.com.ua/p/dzhenifer-stul-1141-product-375-242.jpeg"))
        listCategory.add(CategoryItem(null, "Кровати", "http://v.albinos.com.ua/p/a-042-krokus-barniy-stul-6983-product-375-242.jpeg"))
        listCategory.add(CategoryItem(null, "Люстры", "http://v.albinos.com.ua/p/krokus-c105-stul-4778-product-375-242.jpeg"))

        return listCategory
    }

    private fun initProductList(): ArrayList<ProductItem> {
        val listCategory: ArrayList<ProductItem> = java.util.ArrayList()
        listCategory.add(ProductItem(null, "Стул", "http://www.vashdom.ru/files/articles/6000/6014/2.jpg", 200, false, false,5))
        listCategory.add(ProductItem(null, "Стул1", "http://rusmebelshop.ru/img/image/divan.jpg", 2030, false, false,5))
        listCategory.add(ProductItem(null, "Диван", "http://rusmebelshop.ru/img/image/kreslo1.jpg", 2020, false, true,4))
        listCategory.add(ProductItem(null, "Диван1", "http://rusmebelshop.ru/img/image/kreslo3.jpg", 2010, false, false, 4))
        listCategory.add(ProductItem(null, "Стол", "https://images.clipartlogo.com/files/istock/previews/1048/104875165-wooden-chairs-on-a-white-background-wooden-furniture.jpg", 200, true,false, 1))
        listCategory.add(ProductItem(null, "Стол1", "http://v.albinos.com.ua/p/dzhenifer-stul-1141-product-375-242.jpeg", 2010, true,false, 1))
        listCategory.add(ProductItem(null, "Гардероб", "http://v.albinos.com.ua/p/a-042-krokus-barniy-stul-6983-product-375-242.jpeg", 200, false, true, 2))
        listCategory.add(ProductItem(null, "Декор", "http://v.albinos.com.ua/p/krokus-c105-stul-4778-product-375-242.jpeg", 2020, true,false, 3))
        listCategory.add(ProductItem(null, "Лампа", "http://v.albinos.com.ua/p/krokus-c105-stul-4778-product-375-242.jpeg", 2001, false, true,6))
        listCategory.add(ProductItem(null, "Лампа1", "http://v.albinos.com.ua/p/krokus-c105-stul-4778-product-375-242.jpeg", 2010, false, false,6))
        listCategory.add(ProductItem(null, "Кровать", "http://v.albinos.com.ua/p/krokus-c105-stul-4778-product-375-242.jpeg", 2002, false, false,7))
        listCategory.add(ProductItem(null, "Люстра", "http://v.albinos.com.ua/p/krokus-c105-stul-4778-product-375-242.jpeg", 2030, false, false,8))
        listCategory.add(ProductItem(null, "Люстра1", "http://v.albinos.com.ua/p/krokus-c105-stul-4778-product-375-242.jpeg", 2004, true, true, 8))

        return listCategory
    }

    fun initDB() {
        val listCategory = initCategoryList()
        val listProducts =  initProductList()
        categoriesDao.insert(*listCategory.toTypedArray())
        productsDao.insert(*listProducts.toTypedArray())
        preffsManager.fistInit(true)
    }

    fun getAllProducts(): MutableList<ProductItem>{
        return doAsyncResult { productsDao.getItemList() }.get()
    }

    fun getAllCategory(): MutableList<CategoryItem>{
        return doAsyncResult { categoriesDao.getItemList() }.get()
    }



}