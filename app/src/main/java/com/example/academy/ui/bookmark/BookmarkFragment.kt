package com.example.academy.ui.bookmark


import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.academy.R
import com.example.academy.data.source.local.entity.CourseEntity
import kotlinx.android.synthetic.main.fragment_bookmark.*


class BookmarkFragment : Fragment(), BookmarkFragmentCallback {


    private lateinit var bookmartAdapter: BookmarkAdapter
    private lateinit var bookmarkViewModel: BookmarkViewModel

    fun newInstance(): Fragment {
        return BookmarkFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            bookmarkViewModel = ViewModelProviders.of(this).get(BookmarkViewModel::class.java)
            bookmartAdapter = BookmarkAdapter(context as Activity, this)
            bookmartAdapter.setListCoueses(bookmarkViewModel.getBookmarks())

            rv_bookmark.layoutManager = LinearLayoutManager(context)
            rv_bookmark.setHasFixedSize(true)
            rv_bookmark.adapter = bookmartAdapter
        }
    }

    override fun onShareClick(courseEntity: CourseEntity) {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(activity)
            .setType(mimeType)
            .setChooserTitle("Bagikan aplikasi ini sekarang.")
            .setText(String.format("Segera daftar kelas %s di dicoding.com", courseEntity.title))
            .startChooser()
    }




}
