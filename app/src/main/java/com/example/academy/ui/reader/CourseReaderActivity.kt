package com.example.academy.ui.reader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.academy.R
import com.example.academy.ui.reader.content.ModuleContentFragment
import com.example.academy.ui.reader.list.ModuleListFragment
import com.example.academy.viewmodel.ViewModelFactory

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {


    companion object {
        const val EXTRA_COURSE_ID = "extra_course_id"
    }

    private lateinit var viewModel: CourseReaderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_reader)

        supportActionBar?.setDisplayShowHomeEnabled(true)

        viewModel = obtainViewModel(this)

        val bundle = intent.extras
        if (bundle != null) {
            val courseId = bundle.getString(EXTRA_COURSE_ID)
            if (courseId != null) {
                viewModel.setCourseId(courseId)
                populateFragment()
            }
        }

    }

    override fun moveTo(position: Int, moduleId: String) {
        val fragment = ModuleContentFragment().newInstance()
        supportFragmentManager.beginTransaction().add(R.id.frame_container, fragment, ModuleContentFragment().TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    private fun populateFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(ModuleListFragment().TAG)
        if (fragment == null) {
            fragment = ModuleListFragment().newInstance()
            fragmentTransaction.add(R.id.frame_container, fragment, ModuleListFragment().TAG)
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }

    private fun obtainViewModel(activity: FragmentActivity): CourseReaderViewModel {
        val factory = ViewModelFactory().getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(CourseReaderViewModel::class.java)
    }
}
