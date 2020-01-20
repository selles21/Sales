package br.selles21.sales.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import br.selles21.sales.payment.KeyBoardItem
import br.selles21.sales.payment.KeyBoardItemType
import br.selles21.sales.R
import br.selles21.sales.model.PaymentMethod


@BindingAdapter("app:textvisibility")
fun setVisibilityText(view: View, keyBoardItemType: KeyBoardItemType) {
    view.visibility =
        if (keyBoardItemType == KeyBoardItemType.Numero)
            View.VISIBLE
        else
            View.GONE
}

@BindingAdapter("app:imagevisibility")
fun setVisibilityImage(view: View, keyBoardItemType: KeyBoardItemType) {
    view.visibility =
        if (keyBoardItemType == KeyBoardItemType.Action)
            View.VISIBLE
        else
            View.GONE
}

@BindingAdapter("android:src")
fun setImage(view: View, item: KeyBoardItem) {
    (view as ImageView).setImageResource(
        when {
            item.type == KeyBoardItemType.Action ->
                R.drawable.ic_backspace_24px
            else -> android.R.drawable.ic_menu_info_details
        }
    )
}

@BindingAdapter("android:src")
fun setImage(view: View, item: PaymentMethod) {
    (view as ImageView).setImageResource(item.resource)
}


@BindingAdapter("app:values")
fun setItems(textView: TextView, items: List<String>?) {
    textView.text = items?.joinToString(separator = ", ") { it }
}

