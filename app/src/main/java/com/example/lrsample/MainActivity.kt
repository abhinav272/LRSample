package com.example.lrsample

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Loads [MainFragment].
 */
class MainActivity : FragmentActivity(), BrowseFragment.BrowseFragmentHost {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onMediaClicked(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, MediaDetailsFragment.getInstance(movie), "tagg")
            .addToBackStack("tagg").commit()
    }
}