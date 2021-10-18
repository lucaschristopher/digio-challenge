package br.com.digio.androidtest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class SpotlightAdapter : RecyclerView.Adapter<SpotlightItemViewHolder>() {

    var spotlights = emptyList<Spotlight>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                SpotlightListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotlightItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_spotlight, parent, false)
        return SpotlightItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpotlightItemViewHolder, position: Int) {
        holder.bind(spotlights[position])
    }

    override fun getItemCount(): Int =
        spotlights.size
}