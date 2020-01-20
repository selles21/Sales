package br.selles21.sales.extentions

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View


fun View.toBitmap(width: Int, height: Int): Bitmap {
    if (width > 0 && height > 0) {
        this.measure(
            View.MeasureSpec.makeMeasureSpec(
                (width.toFloat()).convertToPixels(), View.MeasureSpec.EXACTLY
            ),
            View.MeasureSpec.makeMeasureSpec(
                height.toFloat().convertToPixels(), View.MeasureSpec.EXACTLY
            )
        )
    }
    this.layout(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight())

    val bitmap = Bitmap.createBitmap(
        this.getMeasuredWidth(),
        this.getMeasuredHeight(), Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    val background: Drawable = this.getBackground()

    background.draw(canvas)
    this.draw(canvas)
    return bitmap
}