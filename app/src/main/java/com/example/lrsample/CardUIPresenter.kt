package com.example.lrsample

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide

class CardUIPresenter : Presenter() {

    private lateinit var mContext: Context
    private lateinit var defaultCardImage: Drawable

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        mContext = parent!!.context
        defaultCardImage = ContextCompat.getDrawable(mContext, R.drawable.movie)!!
        val cardView = object : ImageCardView(mContext) {
            override fun setSelected(selected: Boolean) {
                val selected_background = ContextCompat.getColor(mContext, R.color.detail_background)
                val default_background = ContextCompat.getColor(mContext, R.color.default_background)
                val color = if (selected) selected_background else default_background
                findViewById<View>(R.id.info_field).setBackgroundColor(color)
                super.setSelected(selected)
            }
        }

        cardView.isFocusable = true
        cardView.isFocusableInTouchMode = true
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val movie = item as Movie
        val cardView = viewHolder!!.view as ImageCardView
        Log.d(TAG, "onBindViewHolder")
        if (movie.cardImageUrl != null) {
            cardView.titleText = movie.title
            cardView.contentText = movie.studio
            cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT)
            Glide.with(viewHolder.view.context)
                .load(movie.cardImageUrl)
                .centerCrop()
                .error(ContextCompat.getDrawable(mContext, R.drawable.movie))
                .into(cardView.mainImageView)
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        Log.d(TAG, "onUnbindViewHolder")
        val cardView = viewHolder!!.view as ImageCardView
        // Remove references to images so that the garbage collector can free up memory
        cardView.badgeImage = null
        cardView.mainImage = null
    }

    companion object {
        private val TAG = "CardUIPresenter"
        private val CARD_WIDTH = 313
        private val CARD_HEIGHT = 176
    }

}
