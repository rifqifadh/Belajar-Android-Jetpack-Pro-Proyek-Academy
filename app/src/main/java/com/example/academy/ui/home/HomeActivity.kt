package com.example.academy.ui.home

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.academy.R
import com.example.academy.ui.academy.AcademyFragment
import com.example.academy.ui.bookmark.BookmarkFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    companion object {
        private const val SELECTED_MENU = "selected_menu"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()

        if (savedInstanceState != null){
            savedInstanceState.getInt(SELECTED_MENU)
        } else {
            nav_view.selectedItemId = R.id.action_home
        }

    }

    private fun init() {
        nav_view.setOnNavigationItemSelectedListener { item ->

            var fragment: Fragment? = null

            when (item.itemId) {
                R.id.action_home -> {
                    fragment = AcademyFragment().newInstance()
                }
                R.id.action_bookmark -> {
                    fragment = BookmarkFragment().newInstance()
                }
            }
            if (fragment != null){
                supportFragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container, fragment)
                    .commit()
            }
            true
        }
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putInt(SELECTED_MENU, nav_view.selectedItemId)
    }
}
