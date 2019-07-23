package com.designloft.repository

import com.designloft.data.PreferencesManager
import com.designloft.database.daos.CategoriesDao
import com.designloft.database.daos.PhotoProductDao
import com.designloft.database.daos.ProductsDao
import com.designloft.database.entities.CategoryEntity
import com.designloft.database.entities.PhotoProductEntity
import com.designloft.database.entities.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class ModelRepository(
    private val preffsManager: PreferencesManager,
    private val categoriesDao: CategoriesDao,
    private val productsDao: ProductsDao,
    private  val photoProductDao: PhotoProductDao
) {

    private fun initCategoryList(): ArrayList<CategoryEntity> {
        val listCategory: ArrayList<CategoryEntity> = ArrayList()
        listCategory.add(CategoryEntity(null, "Столы"))
        listCategory.add(CategoryEntity(null, "Гардероб"))
        listCategory.add(CategoryEntity(null, "Декор"))
        listCategory.add(CategoryEntity(null, "Диваны"))
        listCategory.add(CategoryEntity(null, "Стулья"))
        listCategory.add(CategoryEntity(null, "Лампы"))
        listCategory.add(CategoryEntity(null, "Кровати"))
        listCategory.add(CategoryEntity(null, "Люстры"))

        return listCategory
    }

    private fun initProductList(): ArrayList<ProductEntity> {
        val listProduct: ArrayList<ProductEntity> = ArrayList()
        listProduct.add(ProductEntity(null, "Стул", "Обзор Кресло Гилмор Черный (47331805)\n" +
                "современный стильный дизайн и продуманная эргономичность\n" +
                "комфортное мягкое сиденье в обивке из дышащей ткани\n" +
                "спинка обита эластичной нейлоновой сеткой со вставкой из экокожи\n" +
                "в ассортименте несколько вариантов цветового решения\n" +
                "удобные подлокотники выполнены из черного пластика\n" +
                "прочная устойчивая база изготовлена из хромированного металла\n" +
                "механизм качания с фиксацией в одном положении\n" +
                "регулировка высоты сиденья под рост пользователя\n" +
                "изделие рассчитано на максимальную нагрузку 120 килограмм",200.00, 100.99,false, false,5.0, 25.5, 25.5,5))
        listProduct.add(ProductEntity(null, "Стул1", "Обзор Кресло Гилмор Черный (47331805)\n" +
                "современный стильный дизайн и продуманная эргономичность\n" +
                "комфортное мягкое сиденье в обивке из дышащей ткани\n" +
                "спинка обита эластичной нейлоновой сеткой со вставкой из экокожи\n" +
                "в ассортименте несколько вариантов цветового решения\n" +
                "удобные подлокотники выполнены из черного пластика\n" +
                "прочная устойчивая база изготовлена из хромированного металла\n" +
                "механизм качания с фиксацией в одном положении\n" +
                "регулировка высоты сиденья под рост пользователя\n" +
                "изделие рассчитано на максимальную нагрузку 120 килограмм",0.0, 800.99,false, false,5.0, 25.5, 25.5,5))
        listProduct.add(ProductEntity(null, "Диван", "Обзор Кресло Гилмор Черный (47331805)\n" +
                "современный стильный дизайн и продуманная эргономичность\n" +
                "комфортное мягкое сиденье в обивке из дышащей ткани\n" +
                "спинка обита эластичной нейлоновой сеткой со вставкой из экокожи\n" +
                "в ассортименте несколько вариантов цветового решения\n" +
                "удобные подлокотники выполнены из черного пластика\n" +
                "прочная устойчивая база изготовлена из хромированного металла\n" +
                "механизм качания с фиксацией в одном положении\n" +
                "регулировка высоты сиденья под рост пользователя\n" +
                "изделие рассчитано на максимальную нагрузку 120 килограмм",2020.00, 7000.99,false, true,7.0, 28.5, 29.5,4))
        listProduct.add(ProductEntity(null, "Диван1", "Обзор Кресло Гилмор Черный (47331805)\n" +
                "современный стильный дизайн и продуманная эргономичность\n" +
                "комфортное мягкое сиденье в обивке из дышащей ткани\n" +
                "спинка обита эластичной нейлоновой сеткой со вставкой из экокожи\n" +
                "в ассортименте несколько вариантов цветового решения\n" +
                "удобные подлокотники выполнены из черного пластика\n" +
                "прочная устойчивая база изготовлена из хромированного металла\n" +
                "механизм качания с фиксацией в одном положении\n" +
                "регулировка высоты сиденья под рост пользователя\n" +
                "изделие рассчитано на максимальную нагрузку 120 килограмм",0.0, 6000.99,false, false, 4.0, 25.5, 25.5,4))
        listProduct.add(ProductEntity(null, "Стол", "Обзор Кресло Гилмор Черный (47331805)\n" +
                "современный стильный дизайн и продуманная эргономичность\n" +
                "комфортное мягкое сиденье в обивке из дышащей ткани\n" +
                "спинка обита эластичной нейлоновой сеткой со вставкой из экокожи\n" +
                "в ассортименте несколько вариантов цветового решения\n" +
                "удобные подлокотники выполнены из черного пластика\n" +
                "прочная устойчивая база изготовлена из хромированного металла\n" +
                "механизм качания с фиксацией в одном положении\n" +
                "регулировка высоты сиденья под рост пользователя\n" +
                "изделие рассчитано на максимальную нагрузку 120 килограмм",200.00, 1000.99,true,false, 1.0, 25.5, 25.5,1))
        listProduct.add(ProductEntity(null, "Стол1", "Обзор Кресло Гилмор Черный (47331805)\n" +
                "современный стильный дизайн и продуманная эргономичность\n" +
                "комфортное мягкое сиденье в обивке из дышащей ткани\n" +
                "спинка обита эластичной нейлоновой сеткой со вставкой из экокожи\n" +
                "в ассортименте несколько вариантов цветового решения\n" +
                "удобные подлокотники выполнены из черного пластика\n" +
                "прочная устойчивая база изготовлена из хромированного металла\n" +
                "механизм качания с фиксацией в одном положении\n" +
                "регулировка высоты сиденья под рост пользователя\n" +
                "изделие рассчитано на максимальную нагрузку 120 килограмм",2010.99, 1042.99,true,false, 1.0, 25.5, 25.5,1))
        listProduct.add(ProductEntity(null, "Гардероб", "",0.0,5443.99,false, true, 2.0, 25.5, 25.5,2))
        listProduct.add(ProductEntity(null, "Декор", "Обзор Кресло Гилмор Черный (47331805)\n" +
                "современный стильный дизайн и продуманная эргономичность\n" +
                "комфортное мягкое сиденье в обивке из дышащей ткани\n" +
                "спинка обита эластичной нейлоновой сеткой со вставкой из экокожи\n" +
                "в ассортименте несколько вариантов цветового решения\n" +
                "удобные подлокотники выполнены из черного пластика\n" +
                "прочная устойчивая база изготовлена из хромированного металла\n" +
                "механизм качания с фиксацией в одном положении\n" +
                "регулировка высоты сиденья под рост пользователя\n" +
                "изделие рассчитано на максимальную нагрузку 120 килограмм",2020.99, 3400.99,true,false, 3.0, 25.5, 25.5,3))
        listProduct.add(ProductEntity(null, "Лампа", "Обзор Кресло Гилмор Черный (47331805)\n" +
                "современный стильный дизайн и продуманная эргономичность\n" +
                "комфортное мягкое сиденье в обивке из дышащей ткани\n" +
                "спинка обита эластичной нейлоновой сеткой со вставкой из экокожи\n" +
                "в ассортименте несколько вариантов цветового решения\n" +
                "удобные подлокотники выполнены из черного пластика\n" +
                "прочная устойчивая база изготовлена из хромированного металла\n" +
                "механизм качания с фиксацией в одном положении\n" +
                "регулировка высоты сиденья под рост пользователя\n" +
                "изделие рассчитано на максимальную нагрузку 120 килограмм",2001.99, 4000.99,false, true,6.0, 25.5, 25.5,6))
        listProduct.add(ProductEntity(null, "Лампа1",  "Обзор Кресло Гилмор Черный (47331805)\n" +
                "современный стильный дизайн и продуманная эргономичность\n" +
                "комфортное мягкое сиденье в обивке из дышащей ткани\n" +
                "спинка обита эластичной нейлоновой сеткой со вставкой из экокожи\n" +
                "в ассортименте несколько вариантов цветового решения\n" +
                "удобные подлокотники выполнены из черного пластика\n" +
                "прочная устойчивая база изготовлена из хромированного металла\n" +
                "механизм качания с фиксацией в одном положении\n" +
                "регулировка высоты сиденья под рост пользователя\n" +
                "изделие рассчитано на максимальную нагрузку 120 килограмм",2010.99, 5000.99,false, false,6.0, 25.5, 25.5,6))
        listProduct.add(ProductEntity(null, "Кровать", "Обзор Кресло Гилмор Черный (47331805)\n" +
                "современный стильный дизайн и продуманная эргономичность\n" +
                "комфортное мягкое сиденье в обивке из дышащей ткани\n" +
                "спинка обита эластичной нейлоновой сеткой со вставкой из экокожи\n" +
                "в ассортименте несколько вариантов цветового решения\n" +
                "удобные подлокотники выполнены из черного пластика\n" +
                "прочная устойчивая база изготовлена из хромированного металла\n" +
                "механизм качания с фиксацией в одном положении\n" +
                "регулировка высоты сиденья под рост пользователя\n" +
                "изделие рассчитано на максимальную нагрузку 120 килограмм",2002.99, 2500.99,false, false,7.0, 25.5, 25.5,7))
        listProduct.add(ProductEntity(null, "Люстра", "Обзор Кресло Гилмор Черный (47331805)\n" +
                "современный стильный дизайн и продуманная эргономичность\n" +
                "комфортное мягкое сиденье в обивке из дышащей ткани\n" +
                "спинка обита эластичной нейлоновой сеткой со вставкой из экокожи\n" +
                "в ассортименте несколько вариантов цветового решения\n" +
                "удобные подлокотники выполнены из черного пластика\n" +
                "прочная устойчивая база изготовлена из хромированного металла\n" +
                "механизм качания с фиксацией в одном положении\n" +
                "регулировка высоты сиденья под рост пользователя\n" +
                "изделие рассчитано на максимальную нагрузку 120 килограмм",2030.99, 2300.99,false, false,8.0, 25.5, 25.5,8))
        listProduct.add(ProductEntity(null, "Люстра1", "Обзор Кресло Гилмор Черный (47331805)\n" +
                "современный стильный дизайн и продуманная эргономичность\n" +
                "комфортное мягкое сиденье в обивке из дышащей ткани\n" +
                "спинка обита эластичной нейлоновой сеткой со вставкой из экокожи\n" +
                "в ассортименте несколько вариантов цветового решения\n" +
                "удобные подлокотники выполнены из черного пластика\n" +
                "прочная устойчивая база изготовлена из хромированного металла\n" +
                "механизм качания с фиксацией в одном положении\n" +
                "регулировка высоты сиденья под рост пользователя\n" +
                "изделие рассчитано на максимальную нагрузку 120 килограмм",0.0, 1600.99,true, true, 8.0, 25.5, 25.5,8))

        return listProduct
    }

    private fun initPhoto(): ArrayList<PhotoProductEntity> {
        val listPhotoEntity: ArrayList<PhotoProductEntity> = ArrayList()
        listPhotoEntity.add(PhotoProductEntity(null, 5,1,"https://images.clipartlogo.com/files/istock/previews/1048/104875165-wooden-chairs-on-a-white-background-wooden-furniture.jpg"))
        listPhotoEntity.add(PhotoProductEntity(null, 0,2,"http://v.albinos.com.ua/p/dzhenifer-stul-1141-product-375-242.jpeg"))
        listPhotoEntity.add(PhotoProductEntity(null, 0,2,"https://www.aklas.ua/storage/product/1/1837/420x420/kreslo-atlant--kontur--pl-tilt-chrnyy.jpeg"))
        listPhotoEntity.add(PhotoProductEntity(null, 0,2,"https://www.aklas.ua/storage/product/1/1832/420x420/kreslo-arman--kontur--pl-tilt-chrnyy1559213111.png"))
        listPhotoEntity.add(PhotoProductEntity(null, 4,3,"http://rusmebelshop.ru/img/image/divan.jpg"))
        listPhotoEntity.add(PhotoProductEntity(null,  0,4,"https://img.mebelok.com/image/cache/data/RedKing/greys-duo-akciya/greys-duo-seruy-1-500x500.jpg"))
        listPhotoEntity.add(PhotoProductEntity(null, 1,5,"http://mebelveb.ru/published/publicdata/SM73NPMEBELVEB/attachments/SC/products_pictures/%D0%A8%D0%90%D0%94-%D1%81%D1%82%D0%BE%D0%BB-%D0%9C%D0%B8%D0%BB%D0%BE%D1%80%D0%B4-%D0%BE%D0%B2%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9.jpg"))
        listPhotoEntity.add(PhotoProductEntity(null,  0,6,"http://mebelveb.ru/published/publicdata/SM73NPMEBELVEB/attachments/SC/products_pictures/%D1%8E-A-15-11.jpg"))
        listPhotoEntity.add(PhotoProductEntity(null,  0,6,"http://mebelveb.ru/published/publicdata/SM73NPMEBELVEB/attachments/SC/products_pictures/%D1%8E-A-15-11.jpg"))
        listPhotoEntity.add(PhotoProductEntity(null,  0,6,"http://mebelveb.ru/published/publicdata/SM73NPMEBELVEB/attachments/SC/products_pictures/%D1%8E-A-15-11.jpg"))
        listPhotoEntity.add(PhotoProductEntity(null, 2,7,"http://dostavkaoren.ru/wp-content/uploads/2018/08/paks-garderob__0514828_PE639684_S4.jpg"))
        listPhotoEntity.add(PhotoProductEntity(null, 3,8,"https://www.komus.ru/medias/sys_master/root/hbc/h7d/9170338316318.jpg"))
        listPhotoEntity.add(PhotoProductEntity(null,  6,9,"https://basicdecor.ru/files/media/product_photos/39/65263/big/65263.9.jpg"))
        listPhotoEntity.add(PhotoProductEntity(null, 0,10,"https://basicdecor.ru/files/media/product_photos/d7/65264/big/65264.1.jpg"))
        listPhotoEntity.add(PhotoProductEntity(null, 0,10,"https://basicdecor.ru/files/media/product_photos/d7/65264/big/65264.1.jpg"))
        listPhotoEntity.add(PhotoProductEntity(null, 0,10,"https://basicdecor.ru/files/media/product_photos/d7/65264/big/65264.1.jpg"))
        listPhotoEntity.add(PhotoProductEntity(null,  7,11,"https://morfey.ua/image/cache/catalog/Klen/Skarlet/Skarlet1-480x292.jpg"))
        listPhotoEntity.add(PhotoProductEntity(null, 8,12,"https://regenbogen.com/userfiles/models/products-images/products_580x580/mw-light-avgustina-419010805.jpg"))
        listPhotoEntity.add(PhotoProductEntity(null, 0,13,"https://regenbogen.com/userfiles/models/products-images/products_580x580/mw-light-napoli-686010306.jpg"))
        listPhotoEntity.add(PhotoProductEntity(null, 0,13,"https://regenbogen.com/userfiles/models/products-images/products_580x580/mw-light-napoli-686010306.jpg"))
        listPhotoEntity.add(PhotoProductEntity(null, 0,13,"https://regenbogen.com/userfiles/models/products-images/products_580x580/mw-light-napoli-686010306.jpg"))
        return listPhotoEntity
    }

    suspend fun initDB() {
        withContext(Dispatchers.IO) {
            val listCategory = initCategoryList()
            val listProducts = initProductList()
            val listPhoto = initPhoto()
            categoriesDao.insert(*listCategory.toTypedArray())
            productsDao.insert(*listProducts.toTypedArray())
            photoProductDao.insert(*listPhoto.toTypedArray())
            preffsManager.fistInit(true)
        }
    }

    suspend fun getProductsByCategoryId(categoryId: Int) = withContext(Dispatchers.IO) {
        productsDao.getProductsByCategoryId(categoryId).map {
            val listPhotoProductEntity =
                photoProductDao.getPhotoProductListByProductId(it.id!!)
            ProductEntity.toProduct(it, listPhotoProductEntity)
        }.toMutableList()
    }

    suspend fun getProductById(productId: Int) = withContext(Dispatchers.IO) {
        val productEntity = productsDao.getProductByyId(productId)
        val listPhotoProductEntity = photoProductDao.getPhotoProductListByProductId(productId)
        ProductEntity.toProduct(productEntity, listPhotoProductEntity)
    }

    suspend fun getAllCategory() = withContext(Dispatchers.IO) {
        categoriesDao.getItemList().map {
            val listPhotoProductEntity =
                photoProductDao.getPhotoProductByCategoryId(it.id!!)
            CategoryEntity.toCategory(it, listPhotoProductEntity)
        }.toMutableList()
    }

    suspend fun getFavorites() = withContext(Dispatchers.IO) {
        productsDao.getFavorites().map {
            val listPhotoProductEntity = photoProductDao.getPhotoProductListByProductId(it.id!!)
            ProductEntity.toProduct(it, listPhotoProductEntity)
        }.toMutableList()
    }

    suspend fun updateProduct(productEntity: ProductEntity) =
        withContext(Dispatchers.IO) { productsDao.updateProduct(productEntity) }
}


