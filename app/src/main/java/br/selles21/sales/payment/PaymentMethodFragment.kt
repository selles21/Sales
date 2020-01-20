package br.selles21.sales.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.selles21.sales.R
import br.selles21.sales.databinding.ViewpagerOneBinding
import br.selles21.sales.databinding.ViewpagerTwoBinding

class PaymentMethodFragment(
    var page: Int,
    val paymentViewModel: PaymentViewModel
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = when (page) {
            0 -> inflater.inflate(R.layout.viewpager_one, container, false)
            else -> inflater.inflate(R.layout.viewpager_two, container, false)
        }
        when (page) {
            0 -> ViewpagerOneBinding.bind(view).apply {
                handler = paymentViewModel
                lifecycleOwner = this@PaymentMethodFragment.viewLifecycleOwner
            }
            else -> ViewpagerTwoBinding.bind(view).apply {
                handler = paymentViewModel
                lifecycleOwner = this@PaymentMethodFragment.viewLifecycleOwner
            }
        }

        return view
    }
}
