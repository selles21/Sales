package br.selles21.sales.payment

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.selles21.sales.R
import br.selles21.sales.databinding.ActivityPaymentBinding
import br.selles21.sales.receipt.ReceiptActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private val NUM_PAGES: Int = 2
    private lateinit var keyboardItems: ArrayList<KeyBoardItem>
    private lateinit var activityPaymentBinding: ActivityPaymentBinding
    private val paymentViewModel: PaymentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPaymentBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_payment
        )
        activityPaymentBinding.lifecycleOwner = this


        activityPaymentBinding.rvKeyboard.apply {
            adapter = KeyBoardAdapter(
                paymentViewModel,
                PaymentViewModel.keyboardItems
            )
        }

        bottomSheetBehavior = BottomSheetBehavior.from(activityPaymentBinding.llBottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        observeData()
        activityPaymentBinding.vpPaymentMethod.apply {
            adapter = ScreenSlidePagerAdapter(
                supportFragmentManager,
                lifecycle,
                paymentViewModel
            )
        }

        TabLayoutMediator(
            activityPaymentBinding.tlDots,
            activityPaymentBinding.vpPaymentMethod,
            TabLayoutMediator.TabConfigurationStrategy { _, _ ->
            }).attach()

    }

    private fun observeData() {
        paymentViewModel.valuePayDouble.observe(this, Observer {
            activityPaymentBinding.tvValor.text = it
        })
        paymentViewModel.varLivedata.observe(this, Observer {
            if (it) {
                if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED)
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED)
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            }
        })
        paymentViewModel.bundleLivedata.observe(this, Observer {
            it?.let {
                val intent = Intent(this, ReceiptActivity::class.java)
                intent.putExtras(it)
                startActivity(intent)
            }
        })

    }

    private inner class ScreenSlidePagerAdapter(
        fa: FragmentManager,
        lifecycle: Lifecycle,
        val paymentViewModel: PaymentViewModel
    ) :
        FragmentStateAdapter(fa, lifecycle) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment =
            PaymentMethodFragment(
                position,
                paymentViewModel
            )

    }


}
