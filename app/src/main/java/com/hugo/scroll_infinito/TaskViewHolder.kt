package com.hugo.scroll_infinito

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * ViewHolder para representar cada ítem de tarea en el RecyclerView.
 *
 * @property tvTask TextView que muestra el texto de la tarea.
 * @property ivTaskDone ImageView que representa el estado de la tarea (completada o no).
 *
 * @param view La vista del ítem de tarea que contiene los elementos de interfaz de usuario.
 */
class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTask: TextView = view.findViewById(R.id.tvTask) // Inicializa el TextView para la tarea
    private val ivTaskDone: ImageView = view.findViewById(R.id.ivTaskDone) // Inicializa el ImageView para el estado de la tarea

    /**
     * Renderiza los datos de la tarea en el ViewHolder.
     *
     * @param task El texto de la tarea que se mostrará en el TextView.
     * @param onItemDone Función que se ejecuta cuando se hace clic en el icono de tarea completada.
     */
    fun render(task: String, onItemDone: (Int) -> Unit) {
        tvTask.text = task // Establece el texto del TextView con el nombre de la tarea
        ivTaskDone.setOnClickListener { onItemDone(adapterPosition) } // Configura el listener para el clic en el icono
    }
}
