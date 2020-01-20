package br.selles21.sales.receipt

import android.app.Application
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.selles21.sales.databinding.ItemKeyboardBinding
import br.selles21.sales.extentions.toBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class ReceiptViewModel(application: Application) : AndroidViewModel(application), KoinComponent {

    private var _varLivedata = MutableLiveData<Bitmap>()
    val varLivedata: LiveData<Bitmap> = _varLivedata

    fun start(root: View) {
        CoroutineScope(Dispatchers.IO).launch {
            val convertToBitmap = convertToBitmap(root)
            _varLivedata.postValue(convertToBitmap)
        }

    }

    private fun convertToBitmap(root: View): Bitmap {
        return root.toBitmap(391, 700)
    }

}