package com.berkfaruk.app.movieapp2

import androidx.recyclerview.widget.DiffUtil
import com.berkfaruk.app.movieapp2.domain.model.SearchModel

class MyDiffUtil(private val oldList: List<SearchModel>, private val newList: List<SearchModel>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].imdbID == newList[newItemPosition].imdbID
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}