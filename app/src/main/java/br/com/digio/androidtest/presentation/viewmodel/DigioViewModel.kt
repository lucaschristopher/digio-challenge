package br.com.digio.androidtest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digio.androidtest.domain.model.DigioProducts
import br.com.digio.androidtest.domain.usecase.GetDigioProductsUseCase
import br.com.digio.androidtest.presentation.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DigioViewModel @Inject constructor(
    private val useCase: GetDigioProductsUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<Result<DigioProducts>>(Result.Initial)
    val products = _products.asStateFlow()

    fun getProducts() {
        viewModelScope.launch {
            useCase.invoke()
                .onStart {
                    _products.value = Result.Loading
                }
                .catch { throwable ->
                    _products.value = Result.Failure(throwable.message)
                }
                .collect { products ->
                    _products.value = Result.Success(products)
                }
        }
    }
}
