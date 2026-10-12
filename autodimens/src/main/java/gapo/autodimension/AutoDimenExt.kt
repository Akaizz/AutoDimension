package gapo.autodimension

import android.content.Context
import android.util.DisplayMetrics

fun Context.dpToPx(dp: Number): Float {
    return dp.toFloat() * (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun Context.pxToDp(px: Number): Float {
    return px.toFloat() / (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}