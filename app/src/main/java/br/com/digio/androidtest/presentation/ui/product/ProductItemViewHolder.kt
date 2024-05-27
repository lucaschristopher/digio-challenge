package br.com.digio.androidtest.presentation.ui.product

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.digio.androidtest.R
import br.com.digio.androidtest.databinding.ItemMainProductsBinding
import br.com.digio.androidtest.domain.model.Product
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

internal class ProductItemViewHolder(
    private val binding: ItemMainProductsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {
        with(binding) {
            imgMainItem.contentDescription = product.name
            progressImage.visibility = View.VISIBLE

            Picasso.get()
                .load(product.imageURL)
                .error(R.drawable.ic_alert_circle)
                .into(imgMainItem, object : Callback {
                    override fun onSuccess() {
                        progressImage.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        progressImage.visibility = View.GONE
                    }
                })
        }
    }
}
