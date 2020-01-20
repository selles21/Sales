package br.selles21.sales.receipt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.selles21.sales.R
import br.selles21.sales.databinding.ActivityReceiptBinding
import br.selles21.sales.databinding.ViewReceiptBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_receipt.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class ReceiptActivity : AppCompatActivity() {

    private val receiptViewModel: ReceiptViewModel by viewModel()
    private lateinit var activityReceiptBinding: ActivityReceiptBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityReceiptBinding = DataBindingUtil.setContentView(this, R.layout.activity_receipt)
//        setContentView(R.layout.activity_receipt)
        setSupportActionBar(toolbar)

        title = "PAGAMENTO REALIZADO COM SUCESSO"

        val extras = intent.extras

        extras?.let {
            val inflate = ViewReceiptBinding.inflate(layoutInflater)
            inflate.tvPaymentType.text = it.getString("type")
            inflate.tvValueCompra.text = it.getString("value")
            inflate.tvDate.text = getDateTime()
            inflate.executePendingBindings()

            receiptViewModel.start(inflate.root)
            Snackbar.make(
                bt_confirmar,
                it.getString("type") + "-" + it.getString("value"),
                Snackbar.LENGTH_SHORT
            ).show()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        observeData()
    }

    private fun observeData() {

        receiptViewModel.varLivedata.observe(this, androidx.lifecycle.Observer {
            activityReceiptBinding.ivReceipt.setImageBitmap(it)
        })
    }

    fun getDateTime(): String {

        val date = Date()

        val formatter = SimpleDateFormat("dd/MM/yyyy kk:mm:ss", Locale("pt", "BR"))

        return formatter.format(date)
    }

}
