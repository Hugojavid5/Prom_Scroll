package com.hugo.scroll_infinito

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hugo.scroll_infinito.TaskApplication.Companion.prefs

/**
 * MainActivity de la aplicación que gestiona una lista de tareas y reproduce un sonido al agregar una nueva tarea.
 * La actividad contiene un RecyclerView para mostrar las tareas, un botón para agregar tareas,
 * y un MediaPlayer para reproducir sonidos al realizar ciertas acciones.
 */
class MainActivity : AppCompatActivity() {

    // Botón para añadir una nueva tarea
    lateinit var btnAddTask: Button

    // Campo de texto para ingresar la tarea
    lateinit var etTask: EditText

    // RecyclerView para mostrar la lista de tareas
    lateinit var rvTasks: RecyclerView

    // Adaptador para gestionar la lista de tareas
    lateinit var adapter: TaskAdapter

    // MediaPlayer para reproducir sonidos
    lateinit var mediaPlayer: MediaPlayer

    // Lista mutable que contiene las tareas
    var tasks = mutableListOf<String>()

    /**
     * Método onCreate llamado al iniciar la actividad. Configura la vista y la interfaz de usuario.
     * @param savedInstanceState Contiene datos de estado si la actividad está siendo re-creada.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
    }

    /**
     * Método initUi para inicializar la interfaz de usuario de la actividad.
     */
    private fun initUi() {
        initView()
        initListeners()
        initRecyclerView()
    }

    /**
     * Configura el RecyclerView y carga las tareas guardadas.
     */
    private fun initRecyclerView() {
        tasks = prefs.getTasks() // Obtener tareas guardadas
        rvTasks.layoutManager = LinearLayoutManager(this) // Configurar el diseño lineal
        adapter = TaskAdapter(tasks) { addTask(it.toString()) } // Crear el adaptador con la lista de tareas
        rvTasks.adapter = adapter // Asignar el adaptador al RecyclerView
    }

    /**
     * Añade una nueva tarea a la lista de tareas, guarda la lista actualizada, y reproduce un sonido.
     * @param newTask La nueva tarea que se agregará a la lista.
     */
    private fun addTask(newTask: String) {
        tasks.add(newTask) // Agregar la nueva tarea a la lista
        adapter.notifyDataSetChanged() // Notificar al adaptador que los datos han cambiado
        prefs.saveTasks(tasks) // Guardar la lista actualizada

        // Reproducir sonido si el MediaPlayer está inicializado
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.start()
        }
    }

    /**
     * Establece los listeners de la interfaz de usuario, incluyendo el botón para añadir tareas.
     */
    private fun initListeners() {
        btnAddTask.setOnClickListener { addTask() }
    }

    /**
     * Obtiene el texto de la tarea desde el campo de texto y la agrega a la lista si no está vacía.
     * Luego guarda la lista actualizada y limpia el campo de texto.
     */
    private fun addTask() {
        val taskToAdd: String = etTask.text.toString() // Obtener el texto de la tarea
        if (taskToAdd.isNotEmpty()) { // Verificar que el campo no esté vacío
            tasks.add(taskToAdd) // Añadir la nueva tarea a la lista
            prefs.saveTasks(tasks) // Guardar la lista actualizada
            adapter.notifyDataSetChanged() // Notificar al adaptador que los datos han cambiado
            etTask.setText("") // Limpiar el campo de texto
        }
    }

    /**
     * Inicializa las vistas y enlaza los elementos de la interfaz de usuario con los componentes en el layout.
     */
    private fun initView() {
        btnAddTask = findViewById(R.id.btn_aniadirTarea) // Enlazar el botón de añadir tarea
        etTask = findViewById(R.id.txt_tarea) // Enlazar el campo de texto
        rvTasks = findViewById(R.id.View_listaTarea) // Enlazar el RecyclerView
    }

    /**
     * Método llamado cuando la actividad se destruye. Libera los recursos utilizados por el MediaPlayer.
     */
    override fun onDestroy() {
        super.onDestroy()
        // Liberar el MediaPlayer cuando la actividad se destruya
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
    }
}
