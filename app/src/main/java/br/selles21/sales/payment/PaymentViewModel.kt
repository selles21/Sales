package br.selles21.sales.payment

import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.selles21.sales.extentions.toReaisString
import com.google.android.material.snackbar.Snackbar
import org.koin.core.KoinComponent

class PaymentViewModel(application: Application) : AndroidViewModel(application), KoinComponent {

    companion object {
        val keyboardItems: ArrayList<KeyBoardItem> =
            getKeyBoardItems()

        private fun getKeyBoardItems(): ArrayList<KeyBoardItem> {
            return arrayListOf(
                KeyBoardItem(
                    KeyBoardItemType.Numero,
                    "3"
                ),
                KeyBoardItem(
                    KeyBoardItemType.Numero,
                    "2"
                ),
                KeyBoardItem(
                    KeyBoardItemType.Numero,
                    "1"
                ),
                KeyBoardItem(
                    KeyBoardItemType.Numero,
                    "6"
                ),
                KeyBoardItem(
                    KeyBoardItemType.Numero,
                    "5"
                ),
                KeyBoardItem(
                    KeyBoardItemType.Numero,
                    "4"
                ),
                KeyBoardItem(
                    KeyBoardItemType.Numero,
                    "9"
                ),
                KeyBoardItem(
                    KeyBoardItemType.Numero,
                    "8"
                ),
                KeyBoardItem(
                    KeyBoardItemType.Numero,
                    "7"
                ),
                KeyBoardItem(
                    KeyBoardItemType.Action,
                    ""
                ),
                KeyBoardItem(
                    KeyBoardItemType.Numero,
                    "0"
                )
            )
        }
    }

    private var _valuePay: String = ""

    private var _valuePayDouble = MutableLiveData<String>()
    val valuePayDouble: LiveData<String> = _valuePayDouble

    private var _varLivedata = MutableLiveData<Boolean>()
    val varLivedata: LiveData<Boolean> = _varLivedata

    private var _bundleLivedata = MutableLiveData<Bundle>()
    val bundleLivedata: LiveData<Bundle> = _bundleLivedata

    fun itemPressed(item: KeyBoardItem) {
        when (item.type) {
            KeyBoardItemType.Numero -> {
                if (_valuePay == "") {
                    if (item.value != "0") {
                        _valuePay = _valuePay.plus("" + item.value)
                        _valuePayDouble.value = (_valuePay.toDouble() / 100).toReaisString()
                        _varLivedata.value = true
                    }

                } else
                    if (_valuePay.toDouble() < Int.MAX_VALUE.toFloat() * 1000) {
                        _valuePay = _valuePay.plus("" + item.value)
                        _valuePayDouble.value = (_valuePay.toDouble() / 100).toReaisString()

                        _varLivedata.value = true
                    }
            }
            else -> {
                if (_valuePay.isNotEmpty()) {
                    _valuePay = _valuePay.dropLast(1)
                    if (_valuePay.isNotEmpty())
                        _valuePayDouble.value = (_valuePay.toDouble() / 100).toReaisString()
                    else {
                        _valuePayDouble.value = 0.00.toReaisString()
                        _varLivedata.value = false
                    }
                } else {
                    _valuePayDouble.value = 0.00.toReaisString()
                    _varLivedata.value = false
                }

            }
        }
    }

    fun viewpagerElementPressed(view: View) {
        val text = (view as TextView).text
        val bundle = Bundle()
        bundle.putString("type", text.toString())
        bundle.putString("value", this.valuePayDouble.value)

        _bundleLivedata.value = bundle
    }

}