package com.example.lrsample

import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseFragment
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter

class BrowseFragment : BrowseSupportFragment() {
    private val TAG = "BrowseFragment"
    private lateinit var rowsAdapter: ArrayObjectAdapter
    private lateinit var mList: List<Movie>
    private lateinit var mCategories: Array<String>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupData()
    }

    private fun setupData() {
        mList = MovieList.list;
        mCategories = MovieList.MOVIE_CATEGORY;
        rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardUIPresenter()
        var i = 0L
        for (mCategory in mCategories) {
            var listRowAdapter = ArrayObjectAdapter(cardPresenter).apply {
                for (movie in mList) {
                    add(movie)
                }
            }

            i++
            val headerItem = HeaderItem(i, mCategory);
            rowsAdapter.add(ListRow(headerItem, listRowAdapter))
        }

        val gridHeader = HeaderItem(i, "More Sample :)")
        val gridRowAdapter = ArrayObjectAdapter(GridItemUIPresenter()).apply {
            add(getString(R.string.grid_view))
            add(getString(R.string.error_fragment))
            add(getString(R.string.personal_settings))
        }

        rowsAdapter.add(ListRow(gridHeader, gridRowAdapter))
        adapter = rowsAdapter
        updateRecommendations()
    }

    private fun updateRecommendations() {
        Log.e(TAG, "updateRecommendations: called")
    }

    private fun setupView() {
        title = getString(R.string.browse_title)
        // over title
        headersState = BrowseFragment.HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        // set fastLane (or headers) background color
        brandColor = ContextCompat.getColor(
            context!!,
            R.color.fastlane_background
        )
        // set search icon color
        searchAffordanceColor = ContextCompat.getColor(
            context!!,
            R.color.search_opaque
        )
    }
}