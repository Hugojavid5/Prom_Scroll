package com.hugo.scroll_infinito

import android.app.Application

class TaskApplication : Application() {

    companion object {
        lateinit var prefs: Preferences // Instancia global de Preferences
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Preferences(baseContext) // Inicializar Preferences con el contexto de la aplicación
    }
}
