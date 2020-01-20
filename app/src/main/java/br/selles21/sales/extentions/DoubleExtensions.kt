package br.selles21.sales.extentions

import java.text.NumberFormat
import java.util.*

fun Double.toReaisString(): String =
    NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)