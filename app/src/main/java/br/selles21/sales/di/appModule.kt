package br.selles21.sales.di

import br.selles21.sales.payment.PaymentViewModel
import br.selles21.sales.receipt.ReceiptViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        PaymentViewModel(androidApplication())
    }
    viewModel {
        ReceiptViewModel(androidApplication())
    }
}