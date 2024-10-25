package com.hugo.scroll_infinito

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val tasks: List<String>, private val onItemDone: (Int) -> Unit) : RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        // Infla el diseño del item de tarea
        return TaskViewHolder(layoutInflater.inflate(R.layout.item_task, parent, false))
    }
    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.render(tasks[position], onItemDone) // Renderiza la tarea en el ViewHolder
    }
}
