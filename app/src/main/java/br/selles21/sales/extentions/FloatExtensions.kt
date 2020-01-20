package br.selles21.sales.extentions

import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.roundToInt

fun Float.convertToPixels() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this, Resources.getSystem().displayMetrics
).roundToInt()