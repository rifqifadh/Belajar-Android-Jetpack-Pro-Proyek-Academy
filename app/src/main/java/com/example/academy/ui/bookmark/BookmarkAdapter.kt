package com.example.academy.ui.bookmark

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.academy.R
import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.ui.detail.DetailCourseActivity
import org.jetbrains.anko.find

class BookmarkAdapter(private val activity: Activity, private val callback: BookmarkFragmentCallback) : RecyclerView.Adapter<BookmarkHolder>() {

    private val courses: MutableList<CourseEntity> = mutableListOf()

    fun setListCoueses(listCourses: MutableList<CourseEntity>) {
        courses.clear()
        courses.addAll(listCourses)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkHolder {
        return BookmarkHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.items_bookmark, parent, false)
        )
    }

    override fun getItemCount(): Int = courses.size

    override fun onBindViewHolder(holder: BookmarkHolder, position: Int) {
        holder.bindItem(courses[position])
        holder.btnShare.setOnClickListener { callback.onShareClick(courses[holder.adapterPosition]) }
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, DetailCourseActivity::class.java)
            intent.putExtra(DetailCourseActivity.EXTRA_COURSE, courses[position].courseId)
            activity.startActivity(intent)
        }
    }
}

class BookmarkHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val tvTitle = view.find<TextView>(R.id.tv_item_title)
    private val tvDate = view.find<TextView>(R.id.tv_item_date)
    private val tvDesc = view.find<TextView>(R.id.tv_item_description)
    private val imgPoster = view.find<ImageView>(R.id.img_poster)
    val btnShare = view.find<ImageButton>(R.id.img_share)

    fun bindItem(courseEntity: CourseEntity) {
        tvTitle.text = courseEntity.title
        tvDesc.text = courseEntity.description
        tvDate.text = String.format("Deadline %s", courseEntity.deadline)

        Glide.with(view.context).load(courseEntity.imagePath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_broken_image_black))
            .into(imgPoster)

    }
}
