package com.hugo.scroll_infinito

import android.content.Context
import android.content.SharedPreferences


class Preferences(context: Context) {

    companion object {
        const val PREFS_NAME = "myDatabase" // Nombre del archivo de SharedPreferences
        const val TASKS = "tasks_value" // Clave para almacenar la lista de tareas
    }

    // Inicializa SharedPreferences
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    fun saveTasks(tasks: List<String>) {
        // Convierte la lista de tareas en un conjunto y lo guarda
        prefs.edit().putStringSet(TASKS, tasks.toSet()).apply()
    }
    fun getTasks(): MutableList<String> {
        // Obtiene el conjunto de tareas y lo convierte en una lista mutable
        return prefs.getStringSet(TASKS, emptySet<String>())?.toMutableList() ?: mutableListOf()
    }
}
