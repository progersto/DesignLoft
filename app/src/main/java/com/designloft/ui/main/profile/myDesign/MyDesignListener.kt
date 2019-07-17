package com.designloft.ui.main.profile.myDesign

import com.designloft.database.entities.MyDesignItem

interface MyDesignListener {

    fun onItemDelete(position: MyDesignItem)

    fun onItemClick(position: MyDesignItem)
}