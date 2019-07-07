package com.example.academy.ui.reader.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.academy.R
import com.example.academy.data.source.local.entity.ModuleEntity
import org.jetbrains.anko.find

class ModuleListAdapter(private val listener: MyAdapterClickListener): RecyclerView.Adapter<ModuleListHolder>() {

    private val modules: MutableList<ModuleEntity> = mutableListOf()

    fun setModules(moduleEntity: MutableList<ModuleEntity>) {
        modules.clear()
        modules.addAll(moduleEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleListHolder {
        return ModuleListHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.items_module_list_custom, parent, false)
        )
    }

    override fun getItemCount(): Int = modules.size

    override fun onBindViewHolder(holder: ModuleListHolder, position: Int) {
        holder.bindItem(modules[position])
        holder.itemView.setOnClickListener {
            listener.onItemClicked(holder.adapterPosition, modules[position].mModuleId!!)
        }
    }
}

class ModuleListHolder(view: View): RecyclerView.ViewHolder(view) {

    private val textTitle = view.find<TextView>(R.id.text_module_title)
    private val textLastSeen = view.find<TextView>(R.id.text_last_seen)

    fun bindItem(moduleEntity: ModuleEntity) {
        textTitle.text = moduleEntity.mTitle
    }

}


interface MyAdapterClickListener {
    fun onItemClicked(position: Int, moduleId: String)
}