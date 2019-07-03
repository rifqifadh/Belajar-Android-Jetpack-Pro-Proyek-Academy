package com.example.academy.ui.reader.content


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProviders
import com.example.academy.R
import com.example.academy.data.ContentEntity
import com.example.academy.data.ModuleEntity
import com.example.academy.ui.reader.CourseReaderViewModel
import org.jetbrains.anko.find


class ModuleContentFragment : Fragment() {

    val TAG = ModuleContentFragment::class.java.simpleName

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
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
        webView = view.find(R.id.web_view)
        progressBar = view.find(R.id.progress_bar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(CourseReaderViewModel::class.java)
        }
        viewModel.getSelectedModule()?.let { populateWebView(it) }
    }

    private fun populateWebView(moduleEntity: ModuleEntity) {
        webView.loadData(moduleEntity.contentEntity?.mContent, "text/html", "UTF-8")
    }


}
