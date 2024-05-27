package br.com.digio.androidtest.domain.model

internal data class DigioProducts(
    val cash: Cash,
    val products: List<Product>,
    val spotlight: List<Spotlight>
)
