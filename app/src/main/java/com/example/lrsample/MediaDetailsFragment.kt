package com.example.lrsample

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.app.DetailsSupportFragmentBackgroundController
import androidx.leanback.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.example.lrsample.gen.DetailsActivity

class MediaDetailsFragment : DetailsSupportFragment() {
    private lateinit var mSelectedMovie: Movie
    private lateinit var rowsAdapter: ArrayObjectAdapter
    private val mDetailsBackground = DetailsSupportFragmentBackgroundController(this)

    companion object {
        private const val TAG = "MediaDetailsFragment"

        fun getInstance(movie: Movie) : MediaDetailsFragment {
            val bundle = Bundle()
            bundle.putSerializable("movie", movie)
            val fragment = MediaDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        arguments?.let {
            mSelectedMovie = it.getSerializable("movie") as Movie
        }

        buildDetails()
    }


    private fun buildDetails() {
        val selector = ClassPresenterSelector().apply {
            FullWidthDetailsOverviewRowPresenter(MovieDetailsDescriptionPresenter()).also {
                addClassPresenter(DetailsOverviewRow::class.java, it)
            }
            addClassPresenter(ListRow::class.java, ListRowPresenter())
        }

        rowsAdapter = ArrayObjectAdapter(selector)
        val detailsOverviewRow = DetailsOverviewRow(mSelectedMovie).apply {
            imageDrawable = ContextCompat.getDrawable(context!!, R.drawable.lb_ic_thumb_up)
            addAction(Action(1, "Action 1"))
            addAction(Action(2, "Action 2"))
            addAction(Action(3, "Action 3"))

            Glide.with(context!!)
                .asBitmap()
                .load(mSelectedMovie.backgroundImageUrl)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onLoadCleared(placeholder: Drawable?) {

                    }

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        setImageBitmap(context, resource)
                        mDetailsBackground.enableParallax()
                        mDetailsBackground.setCoverBitmap(resource)
                    }

                })

        }

        rowsAdapter.add(detailsOverviewRow)

        val suggestionsRowAdapter = ArrayObjectAdapter(GridItemUIPresenter()).apply {
            add("Suggestion 1")
            add("Suggestion 2")
            add("Suggestion 3")
            add("Suggestion 4")
        }

        val header = HeaderItem(0, "Related/Suggestions")
        rowsAdapter.add(ListRow(header, suggestionsRowAdapter))
        adapter = rowsAdapter
    }
}