package com.example.academy.ui.reader.content


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.academy.R
import com.example.academy.data.source.local.entity.ModuleEntity
import com.example.academy.ui.reader.CourseReaderViewModel
import com.example.academy.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_module_content.*
import org.jetbrains.anko.find


class ModuleContentFragment : Fragment() {

    val TAG = ModuleContentFragment::class.java.simpleName

    private lateinit var viewModel: CourseReaderViewModel

    fun newInstance(): Fragment {
        return ModuleContentFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress_bar.visibility = View.VISIBLE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            viewModel = obtainViewModel(it)
        }
        viewModel.getSelectedModule()?.observe(this, moduleEntity)
    }

    private val moduleEntity = Observer<ModuleEntity> {
            populateWebView(it)
        progress_bar.visibility = View.GONE
    }

    private fun populateWebView(moduleEntity: ModuleEntity?) {
        web_view.loadData(moduleEntity?.contentEntity?.mContent, "text/html", "UTF-8")
    }

    private fun obtainViewModel(activity: FragmentActivity): CourseReaderViewModel {
        val factory = ViewModelFactory().getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(CourseReaderViewModel::class.java)
    }

}
