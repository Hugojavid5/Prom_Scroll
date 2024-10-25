package com.hugo.scroll_infinito

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Adaptador para gestionar la lista de tareas en un RecyclerView.
 *
 * @property tasks Lista de tareas que se mostrarán en el RecyclerView.
 * @property onItemDone Función que se ejecuta cuando un ítem de la lista se marca como hecho.
 */
class TaskAdapter(private val tasks: List<String>, private val onItemDone: (Int) -> Unit) : RecyclerView.Adapter<TaskViewHolder>() {

    /**
     * Crea un nuevo ViewHolder y lo infla desde el layout de item de tarea.
     *
     * @param parent El ViewGroup al que se le asignará el ViewHolder.
     * @param viewType El tipo de vista (no se utiliza en este caso).
     * @return Un nuevo TaskViewHolder con el layout inflado.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        // Infla el diseño del item de tarea
        return TaskViewHolder(layoutInflater.inflate(R.layout.item_task, parent, false))
    }

    /**
     * Devuelve el número total de tareas en la lista.
     *
     * @return Cantidad de tareas.
     */
    override fun getItemCount() = tasks.size

    /**
     * Asocia los datos de una tarea con el ViewHolder en la posición dada.
     *
     * @param holder El ViewHolder al que se le asignará la tarea.
     * @param position La posición de la tarea en la lista.
     */
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.render(tasks[position], onItemDone) // Renderiza la tarea en el ViewHolder
    }
}
