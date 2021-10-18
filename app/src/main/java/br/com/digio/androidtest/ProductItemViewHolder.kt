package br.com.digio.androidtest

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class ProductItemViewHolder constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    private val imageView = itemView.findViewById<ImageView>(R.id.imgMainItem)
    private val progress = itemView.findViewById<ProgressBar>(R.id.progressImage)

    fun bind(product: Product) {

        imageView.contentDescription = product.name
        progress.visibility = View.VISIBLE

        Picasso.get()
            .load(product.imageURL)
            .error(R.drawable.ic_alert_circle)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    progress.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    progress.visibility = View.GONE
                }
            })
    }
}
