package com.designloft.utils

import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.roundOffDecimal(): String? {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.FLOOR
    return df.format(this)
}

fun Double.roundOffDecimalOne(): String? {
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.FLOOR
    return df.format(this)
}