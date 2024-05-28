package br.com.digio.androidtest.data.mapper

import br.com.digio.androidtest.data.model.CashResponse
import br.com.digio.androidtest.data.model.DigioProductsResponse
import br.com.digio.androidtest.data.model.ProductResponse
import br.com.digio.androidtest.data.model.SpotlightResponse
import br.com.digio.androidtest.domain.model.Cash
import br.com.digio.androidtest.domain.model.DigioProducts
import br.com.digio.androidtest.domain.model.Product
import br.com.digio.androidtest.domain.model.Spotlight

internal fun DigioProductsResponse.toDomain() = DigioProducts(
    cash = this.cash?.toDomain() ?: Cash(),
    products = this.products?.map { it.toDomain() } ?: listOf(),
    spotlight = this.spotlight?.map { it.toDomain() } ?: listOf(),
)

internal fun CashResponse.toDomain() = Cash(
    bannerURL = bannerURL.orEmpty(),
    title = title.orEmpty(),
    description = this.description.orEmpty()
)

internal fun ProductResponse.toDomain() = Product(
    imageURL = imageURL.orEmpty(),
    name = name.orEmpty(),
    description = description.orEmpty()
)

internal fun SpotlightResponse.toDomain() = Spotlight(
    bannerURL = bannerURL.orEmpty(),
    name = name.orEmpty(),
    description = description.orEmpty()
)
