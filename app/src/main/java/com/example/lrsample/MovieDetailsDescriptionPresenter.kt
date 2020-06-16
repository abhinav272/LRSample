package com.example.lrsample

import androidx.leanback.widget.AbstractDetailsDescriptionPresenter

class MovieDetailsDescriptionPresenter : AbstractDetailsDescriptionPresenter() {
    override fun onBindDescription(vh: ViewHolder?, item: Any?) {
        val movie = item as Movie
        vh?.let {
            it.title.text = movie.title
            it.body.text = movie.description
            it.subtitle.text = movie.studio
        }
    }
}