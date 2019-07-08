package com.example.academy.ui.academy


import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.NonNull
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.academy.R
import com.example.academy.utils.DataDummy
import com.example.academy.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_academy.*


class AcademyFragment : Fragment() {

    private lateinit var academyAdapter: AcademyAdapter
    private lateinit var academyViewModel: AcademyViewModel


    fun newInstance(): Fragment {
        return AcademyFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_academy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
//            academyViewModel = ViewModelProviders.of(this).get(AcademyViewModel::class.java)
            academyViewModel = obtainViewModel(it)
            academyAdapter = AcademyAdapter(context as Activity)
//            academyAdapter.setListCourses(listCourses)
            academyViewModel.getCourse()?.let {
                    it1 -> academyAdapter.setListCourses(it1) }

            rv_academy.layoutManager = LinearLayoutManager(context)
            rv_academy.setHasFixedSize(true)
            rv_academy.adapter = academyAdapter
        }
    }

    @NonNull
    private fun obtainViewModel(activity: FragmentActivity): AcademyViewModel {
        val factory = ViewModelFactory().getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(AcademyViewModel::class.java)
    }
}
