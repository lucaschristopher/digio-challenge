package br.com.digio.androidtest

import br.com.digio.androidtest.data.service.DigioService
import br.com.digio.androidtest.domain.model.DigioProducts
import com.google.gson.Gson

class ExampleService(
    private val service: DigioService
) {

    fun mockDigioProducts(): DigioProducts =
        Gson().fromJson(
            """
                {
                  "spotlight": [
                    {
                      "name": "Recarga",
                      "bannerURL": "https://s3-sa-east-1.amazonaws.com/digio-exame/recharge_banner.png",
                      "description": "Agora ficou mais fácil colocar créditos no seu celular! A digio Store traz a facilidade de fazer recargas... direto pelo seu aplicativo, com toda segurança e praticidade que você procura."
                    }
                  ],
                  "products": [
                    {
                      "name": "XBOX",
                      "imageURL": "https://s3-sa-east-1.amazonaws.com/digio-exame/xbox_icon.png",
                      "description": "Com o e-Gift Card Xbox você adquire créditos para comprar games, música, filmes, programas de TV e muito mais!"
                    }
                  ],
                  "cash": {
                    "title": "digio Cash",
                    "bannerURL": "https://s3-sa-east-1.amazonaws.com/digio-exame/cash_banner.png",
                    "description": "Dinheiro na conta sem complicação. Transfira parte do limite do seu cartão para sua conta."
                  }
                }
            """.trimIndent(),
            DigioProducts::class.java
        )

    fun example(): DigioProducts {
        val products = service.getProducts().execute()

        return products.body() ?: mockDigioProducts()
    }
}