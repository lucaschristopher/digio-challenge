package br.com.digio.androidtest.data.model

internal class DigioProductsResponse(
    val cash: CashResponse?,
    val products: List<ProductResponse>?,
    val spotlight: List<SpotlightResponse>?
)