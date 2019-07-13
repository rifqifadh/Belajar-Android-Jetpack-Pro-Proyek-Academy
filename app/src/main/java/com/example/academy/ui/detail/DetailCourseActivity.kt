package com.example.academy.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.academy.R
import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.data.source.local.entity.ModuleEntity
import com.example.academy.ui.reader.CourseReaderActivity
import com.example.academy.ui.reader.CourseReaderActivity.Companion.EXTRA_COURSE_ID
import com.example.academy.viewmodel.ViewModelFactory

import kotlinx.android.synthetic.main.activity_detail_course.*
import kotlinx.android.synthetic.main.content_detail_course.*
import org.jetbrains.anko.activityManager




class DetailCourseActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    private lateinit var detailCourseAdapter: DetailCourseAdapter
    private lateinit var viewModel: DetailCourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailCourseAdapter = DetailCourseAdapter()

        viewModel = obtainViewModel(this)
        progress_bar.visibility = View.VISIBLE

        val extras = intent.extras
        if (extras != null) {
            val courseId = extras.getString(EXTRA_COURSE)
            if (courseId != null) {
                viewModel.setCourseId(courseId)
            }
        }

        viewModel.getCourse().observe(this, courseEntity)
        viewModel.getModules().observe(this, moduleEntity)

        rv_module.isNestedScrollingEnabled = false
        rv_module.layoutManager = LinearLayoutManager(this)
        rv_module.setHasFixedSize(true)
        rv_module.adapter = detailCourseAdapter

        val dividerItemDecoration = DividerItemDecoration(rv_module.context, DividerItemDecoration.VERTICAL)
        rv_module.addItemDecoration(dividerItemDecoration)
    }

    private val courseEntity = Observer<CourseEntity> {  CourseEntity ->
        CourseEntity?.let {
            populateCourse(it)
            progress_bar.visibility = View.GONE
        }
    }

    private val moduleEntity = Observer<MutableList<ModuleEntity>?> { modules ->
        modules?.let {
            detailCourseAdapter.setModules(it)
            detailCourseAdapter.notifyDataSetChanged()
            progress_bar.visibility = View.GONE
        }
    }


    private fun populateCourse(courseEntity: CourseEntity?) {

        text_title.text = courseEntity?.title
        text_description.text = courseEntity?.description
        text_date.text = String.format("Deadline %s", courseEntity?.deadline)

        Glide.with(applicationContext).load(courseEntity?.imagePath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_broken_image_black))
            .into(image_poster)

        btn_start.setOnClickListener {
            val intent = Intent(this, CourseReaderActivity::class.java)
            intent.putExtra(EXTRA_COURSE_ID, courseEntity?.courseId)
            startActivity(intent)
        }
    }

    @NonNull
    private fun obtainViewModel(activity: FragmentActivity): DetailCourseViewModel {
        val factory = ViewModelFactory().getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(DetailCourseViewModel::class.java)
    }
}
