package com.designloft.ui.main.profile.myDesign

import com.designloft.database.entities.MyDesignEntity

interface MyDesignListener {

    fun onItemDelete(position: MyDesignEntity)

    fun onItemClick(position: MyDesignEntity)
}