package com.hugo.scroll_infinito

import android.app.Application

/**
 * Clase personalizada de aplicación para manejar la inicialización global.
 *
 * Proporciona una instancia de la clase [Preferences] para ser utilizada en toda la aplicación.
 */
class TaskApplication : Application() {

    companion object {
        lateinit var prefs: Preferences // Instancia global de Preferences
    }

    /**
     * Método llamado al crear la aplicación.
     *
     * Inicializa la instancia de [Preferences] con el contexto de la aplicación.
     */
    override fun onCreate() {
        super.onCreate()
        prefs = Preferences(baseContext) // Inicializar Preferences con el contexto de la aplicación
    }
}
