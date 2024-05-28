package br.com.digio.androidtest.data.service

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

object MockApiUtil {

    val successResponse = """
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
        """.trimIndent()

    private val errorResponse = """
            {
                "code": "SPN-344",
                "message": "Can't access this operation"
            }
        """.trimIndent()

    private val errorNotFoundResponse = """
            {
                "message": "Operation not found"
            }
        """.trimIndent()

    val mockSuccess by lazy {
        MockResponse()
            .setResponseCode(200)
            .setBody(successResponse)
    }

    val mockError by lazy {
        MockResponse()
            .setResponseCode(400)
            .setBody(errorResponse)
    }

    val mockNotFound by lazy {
        MockResponse()
            .setResponseCode(404)
            .setBody(errorNotFoundResponse)
    }

    val dispatcherSuccess = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/sandbox/products" -> mockSuccess
                else -> mockNotFound
            }
        }
    }

    val dispatcherError = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/sandbox/products" -> mockError
                else -> mockNotFound
            }
        }
    }
}