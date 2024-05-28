package br.com.digio.androidtest.presentation.ui

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.digio.androidtest.R
import br.com.digio.androidtest.databinding.ActivityMainBinding
import br.com.digio.androidtest.domain.model.DigioProducts
import br.com.digio.androidtest.presentation.ui.product.ProductAdapter
import br.com.digio.androidtest.presentation.ui.spotlight.SpotlightAdapter
import br.com.digio.androidtest.presentation.utils.Result
import br.com.digio.androidtest.presentation.utils.hide
import br.com.digio.androidtest.presentation.utils.show
import br.com.digio.androidtest.presentation.viewmodel.DigioViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<DigioViewModel>()

    private val productAdapter: ProductAdapter by lazy { ProductAdapter() }
    private val spotlightAdapter: SpotlightAdapter by lazy { SpotlightAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        this.setupRecyclers()
        this.setupObservers()
        this.viewModel.getProducts()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.products.collect { result ->
                when (result) {
                    is Result.Loading -> handleLoadingState()
                    is Result.Failure -> handleFailureState()
                    is Result.Success -> {
                        handleSuccessState(result.data)
                        setupDigioCashText()
                    }

                    else -> Unit
                }
            }
        }
    }

    private fun setupRecyclers() {
        binding.recyMainProducts.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = productAdapter
        }

        binding.recyMainSpotlight.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = spotlightAdapter
        }

    }

    private fun handleLoadingState() {
        binding.body.hide()
        binding.errorDigioContainer.container.hide()
        binding.loadDigioContainer.progressDigio.show()
    }

    private fun handleSuccessState(result: DigioProducts) {
        productAdapter.products = result.products
        spotlightAdapter.spotlights = result.spotlight

        binding.errorDigioContainer.container.hide()
        binding.loadDigioContainer.progressDigio.hide()
        binding.body.show()
    }

    private fun handleFailureState() {
        val message = getString(R.string.error)

        binding.body.hide()
        binding.loadDigioContainer.progressDigio.hide()
        binding.errorDigioContainer.container.show()

        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }


    private fun setupDigioCashText() {
        val digioCacheText = getString(R.string.digio_cache)
        binding.txtMainDigioCash.text = SpannableString(digioCacheText).apply {
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(this@MainActivity, R.color.blue_darker)
                ),
                START_SPAN_TEXT_INDEX,
                MIDDLE_SPAN_TEXT_INDEX,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(this@MainActivity, R.color.font_color_digio_cash)
                ),
                END_SPAN_TEXT_INDEX,
                digioCacheText.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}

private const val START_SPAN_TEXT_INDEX = 0
private const val MIDDLE_SPAN_TEXT_INDEX = 5
private const val END_SPAN_TEXT_INDEX = 6
