package com.hugo.scroll_infinito

import android.content.Context
import android.content.SharedPreferences

/**
 * Clase que maneja el almacenamiento de preferencias utilizando SharedPreferences.
 *
 * @param context Contexto de la aplicación para acceder a SharedPreferences.
 */
class Preferences(context: Context) {

    companion object {
        const val PREFS_NAME = "myDatabase" // Nombre del archivo de SharedPreferences
        const val TASKS = "tasks_value" // Clave para almacenar la lista de tareas
    }

    // Inicializa SharedPreferences
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    /**
     * Guarda una lista de tareas en SharedPreferences.
     *
     * @param tasks Lista de tareas a almacenar.
     */
    fun saveTasks(tasks: List<String>) {
        // Convierte la lista de tareas en un conjunto y lo guarda
        prefs.edit().putStringSet(TASKS, tasks.toSet()).apply()
    }

    /**
     * Recupera la lista de tareas almacenadas en SharedPreferences.
     *
     * @return Lista mutable de tareas.
     */
    fun getTasks(): MutableList<String> {
        // Obtiene el conjunto de tareas y lo convierte en una lista mutable
        return prefs.getStringSet(TASKS, emptySet<String>())?.toMutableList() ?: mutableListOf()
    }
}
