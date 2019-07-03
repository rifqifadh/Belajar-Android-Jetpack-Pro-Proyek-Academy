package com.example.academy.ui.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.academy.R
import com.example.academy.data.ModuleEntity
import org.jetbrains.anko.find

class DetailCourseAdapter: RecyclerView.Adapter<DetailHolder>() {

    private val  modules: MutableList<ModuleEntity> = mutableListOf()

    fun setModules(moduleEntity: MutableList<ModuleEntity>) {
        modules.clear()
        modules.addAll(moduleEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {
        return DetailHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.items_modules_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        Log.d("Size: ", modules.size.toString())
        return modules.size
    }

    override fun onBindViewHolder(holder: DetailHolder, position: Int) {
        holder.bindItem(modules[position])
    }
}

class DetailHolder(view: View): RecyclerView.ViewHolder(view){

    private val tvTitle = view.find<TextView>(R.id.text_module_title)

    fun bindItem(moduleEntity: ModuleEntity) {
        tvTitle.text = moduleEntity.mTitle
        Log.d("TAG", moduleEntity.mTitle)
    }

}
