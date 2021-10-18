package br.com.digio.androidtest

import androidx.recyclerview.widget.DiffUtil

class SpotlightListDiffCallback constructor(
    private val oldList: List<Spotlight>,
    private val newList: List<Spotlight>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int =
        oldList.size

    override fun getNewListSize(): Int =
        newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].name == newList[newItemPosition].name

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        true
}
