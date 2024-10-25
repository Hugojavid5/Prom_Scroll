package com.hugo.scroll_infinito
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTask: TextView = view.findViewById(R.id.tvTask) // Inicializa el TextView para la tarea
    private val ivTaskDone: ImageView = view.findViewById(R.id.ivTaskDone) // Inicializa el ImageView para el estado de la tarea

    fun render(task: String, onItemDone: (Int) -> Unit) {
        tvTask.text = task // Establece el texto del TextView con el nombre de la tarea
        ivTaskDone.setOnClickListener { onItemDone(adapterPosition) } // Configura el listener para el clic en el icono
    }
}
