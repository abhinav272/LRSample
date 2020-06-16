package com.example.lrsample

import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.leanback.widget.Presenter
import com.example.lrsample.gen.MainFragment

class GridItemUIPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
        val view = TextView(parent.context)
        view.layoutParams = ViewGroup.LayoutParams(
            GRID_ITEM_WIDTH,
            GRID_ITEM_HEIGHT
        )
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        view.setBackgroundColor(
            ContextCompat.getColor(view.context,
            R.color.default_background
        ))
        view.setTextColor(Color.WHITE)
        view.gravity = Gravity.CENTER
        return Presenter.ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {
        (viewHolder.view as TextView).text = item as String
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {}

    companion object {
        private val GRID_ITEM_WIDTH = 200
        private val GRID_ITEM_HEIGHT = 200
    }

}
