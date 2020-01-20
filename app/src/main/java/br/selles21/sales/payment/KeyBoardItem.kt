package br.selles21.sales.payment

data class KeyBoardItem(val type: KeyBoardItemType, val value: String)

enum class KeyBoardItemType {
    Numero, Action
}
