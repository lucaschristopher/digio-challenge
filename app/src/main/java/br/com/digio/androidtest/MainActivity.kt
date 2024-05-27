package br.com.digio.androidtest

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.digio.androidtest.data.service.DigioService
import br.com.digio.androidtest.domain.model.DigioProducts
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var txtMainDigioCash: TextView
    private lateinit var recyMainProducts: RecyclerView
    private lateinit var recyMainSpotlight: RecyclerView
    private lateinit var body: ConstraintLayout
    private lateinit var loadDigioContainer: ConstraintLayout

    private val url = "https://7hgi9vtkdc.execute-api.sa-east-1.amazonaws.com/"

    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val service: DigioService by lazy {
        retrofit.create(DigioService::class.java)
    }

    private val productAdapter: ProductAdapter by lazy {
        ProductAdapter()
    }

    private val spotlightAdapter: SpotlightAdapter by lazy {
        SpotlightAdapter()
    }

    override fun onResume() {
        super.onResume()
        txtMainDigioCash = findViewById(R.id.txtMainDigioCash)
        recyMainProducts = findViewById(R.id.recyMainProducts)
        recyMainSpotlight = findViewById(R.id.recyMainSpotlight)
        body = findViewById(R.id.body)
        loadDigioContainer = findViewById(R.id.loadDigioContainer)

        recyMainProducts.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyMainProducts.adapter = productAdapter

        recyMainSpotlight.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyMainSpotlight.adapter = spotlightAdapter

        body.visibility = View.GONE
        loadDigioContainer.visibility = View.VISIBLE

        service.getProducts()
            .enqueue(object : Callback<DigioProducts> {
                override fun onResponse(
                    call: Call<DigioProducts>,
                    response: Response<DigioProducts>
                ) {
                    loadDigioContainer.visibility = View.GONE
                    body.visibility = View.VISIBLE

                    productAdapter.products = response.body()!!.products
                    spotlightAdapter.spotlights = response.body()!!.spotlight
                }

                override fun onFailure(call: Call<DigioProducts>, t: Throwable) {
                    val message = getString(R.string.error)

                    loadDigioContainer.visibility = View.GONE
                    body.visibility = View.GONE

                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                }
            })

        setupDigioCashText()
    }

    private fun setupDigioCashText() {
        val digioCacheText = "digio Cache"
        txtMainDigioCash.text = SpannableString(digioCacheText).apply {
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(this@MainActivity, R.color.blue_darker)
                ),
                0,
                5,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(this@MainActivity, R.color.font_color_digio_cash)
                ),
                6,
                digioCacheText.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}