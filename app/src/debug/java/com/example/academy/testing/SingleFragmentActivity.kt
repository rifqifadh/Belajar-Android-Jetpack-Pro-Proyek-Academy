package com.example.academy.testing

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.academy.R

class SingleFragmentActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = FrameLayout(this) as View
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT,
            Gravity.CENTER
        )
        view.layoutParams = params
        view.id = R.id.container
        setContentView(view)

    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment, "TEST")
            .commit()
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment)
            .commit()
    }
}