package com.designloft.utils

import android.content.res.Resources
import kotlin.math.roundToInt

object DimenUtils{
    fun dpToPx(dp: Float): Int {
        val density = Resources.getSystem().displayMetrics.density
        return (dp * density).roundToInt()
    }
}