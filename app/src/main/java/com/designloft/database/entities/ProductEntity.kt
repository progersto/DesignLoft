package com.designloft.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.designloft.models.Product

@Entity(tableName = "ProductEntity")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "old_price")
    var oldPrice: Double = 0.0,

    @ColumnInfo(name = "price")
    var price: Double = 0.0,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,

    @ColumnInfo(name = "sale")
    var sale: Boolean = false,

    @ColumnInfo(name = "height")
    var height: Double = 0.0,

    @ColumnInfo(name = "width")
    var width: Double = 0.0,

    @ColumnInfo(name = "length")
    var length: Double = 0.0,

    @ColumnInfo(name = "category_id")
    var categoryId: Int = 0

) {
    companion object {

        fun toProduct(productEntity: ProductEntity, photoList: List<PhotoProductEntity>) =
            Product(
                id = productEntity.id!!,
                name = productEntity.name,
                description= productEntity.description,
                imageList = photoList as MutableList<PhotoProductEntity>,
                oldPrice = productEntity.oldPrice,
                price = productEntity.price,
                favorite = productEntity.favorite,
                sale = productEntity.sale,
                height = productEntity.height,
                width = productEntity.width,
                length = productEntity.length,
                categoryId = productEntity.categoryId
            )

        fun toProductEntity(product: Product) =
            ProductEntity(
                id = product.id,
                name = product.name,
                description= product.description,
                oldPrice = product.oldPrice,
                price = product.price,
                favorite = product.favorite,
                sale = product.sale,
                height = product.height,
                width = product.width,
                length = product.length,
                categoryId = product.categoryId
            )
    }
}