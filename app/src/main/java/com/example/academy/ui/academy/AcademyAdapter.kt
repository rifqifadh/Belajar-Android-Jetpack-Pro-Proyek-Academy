package com.example.academy.ui.academy

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.academy.R
import com.example.academy.data.CourseEntity
import com.example.academy.ui.detail.DetailCourseActivity
import org.jetbrains.anko.find

class AcademyAdapter(private val activity: Activity) : RecyclerView.Adapter<Holder>() {

    private val mCourses: MutableList<CourseEntity> = mutableListOf()

    private fun getListCourses(): MutableList<CourseEntity> {
        return mCourses
    }

    fun setListCourses(listCourses: MutableList<CourseEntity>) {
        mCourses.clear()
        mCourses.addAll(listCourses)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.items_academy, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return getListCourses().size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindItem(getListCourses()[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, DetailCourseActivity::class.java)
            intent.putExtra(DetailCourseActivity.EXTRA_COURSE, getListCourses()[position].courseId)
            activity.startActivity(intent)
        }
    }


}

class Holder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val tvTitle = view.find<TextView>(R.id.tv_item_title)
    private val tvDate = view.find<TextView>(R.id.tv_item_date)
    private val tvDesc = view.find<TextView>(R.id.tv_item_description)
    private val imgPoster = view.findViewById<ImageView>(R.id.img_poster)

    fun bindItem(courseEntity: CourseEntity) {
        tvTitle.text = courseEntity.title
        tvDesc.text = courseEntity.description
        tvDate.text = String.format("Deadline %s", courseEntity.deadline)

        Glide.with(view.context).load(courseEntity.imagePath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_broken_image_black)).into(imgPoster)

    }
}
