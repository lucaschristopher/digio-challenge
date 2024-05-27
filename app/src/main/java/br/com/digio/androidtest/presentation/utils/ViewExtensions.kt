package br.com.digio.androidtest.presentation.utils

import android.view.View

internal fun View.hide() {
    this.visibility = View.GONE
}

internal fun View.show() {
    this.visibility = View.VISIBLE
}
