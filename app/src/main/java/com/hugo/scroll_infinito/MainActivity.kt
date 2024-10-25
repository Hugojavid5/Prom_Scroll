package com.hugo.scroll_infinito
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hugo.scroll_infinito.TaskApplication.Companion.prefs

class MainActivity : AppCompatActivity() {

    lateinit var btnAddTask: Button // Botón para añadir una nueva tarea
    lateinit var etTask: EditText // Campo de texto para ingresar la tarea
    lateinit var rvTasks: RecyclerView // RecyclerView para mostrar la lista de tareas

    lateinit var adapter: TaskAdapter // Adaptador para gestionar la lista de tareas
    lateinit var mediaPlayer: MediaPlayer // Declarar el MediaPlayer para reproducir sonidos

    var tasks = mutableListOf<String>() // Lista mutable que contiene las tareas


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()

        // Inicializar MediaPlayer con el archivo de sonido
        //mediaPlayer = MediaPlayer.create(this, R.raw.delete_sound.mp3)
    }
    private fun initUi() {
        initView()
        initListeners()
        initRecyclerView()
    }


    private fun initRecyclerView() {
        tasks = prefs.getTasks() // Obtener tareas guardadas
        rvTasks.layoutManager = LinearLayoutManager(this) // Configurar el diseño lineal
        adapter = TaskAdapter(tasks) { addTask(it.toString()) } // Crear el adaptador con la lista de tareas
        rvTasks.adapter = adapter // Asignar el adaptador al RecyclerView
    }

    private fun addTask(newTask: String) {
        tasks.add(newTask) // Agregar la nueva tarea a la lista
        adapter.notifyDataSetChanged() // Notificar al adaptador que los datos han cambiado
        prefs.saveTasks(tasks) // Guardar la lista actualizada

        // Reproducir sonido de inserción
        mediaPlayer.start()
    }



    private fun initListeners() {
        btnAddTask.setOnClickListener { addTask() }
    }


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
     * Inicializa las vistas y enlaza los elementos de la interfaz de usuario.
     */
    private fun initView() {
        btnAddTask = findViewById(R.id.btn_aniadirTarea) // Enlazar el botón de añadir tarea
        etTask = findViewById(R.id.txt_tarea) // Enlazar el campo de texto
        rvTasks = findViewById(R.id.View_listaTarea) // Enlazar el RecyclerView
    }

    /**
     * Se llama cuando la actividad se destruye.
     * Libera los recursos utilizados por el MediaPlayer.
     */
    override fun onDestroy() {
        super.onDestroy()
        // Liberar el MediaPlayer cuando la actividad se destruya
        mediaPlayer.release()
    }
}