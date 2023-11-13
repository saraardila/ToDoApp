package com.nawin.todoapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nawin.cursokotlin.todoapp.CategoriesAdapter
import com.nawin.cursokotlin.todoapp.Task
import com.nawin.cursokotlin.todoapp.TaskCategory
import com.nawin.cursokotlin.todoapp.TaskCategory.*
import com.nawin.cursokotlin.todoapp.TasksAdapter
import java.text.FieldPosition

class TodoActivity : AppCompatActivity() {

    private val categories = listOf( //-->> Lo que recibe aqui es lo que va a pintar

        Business,
        Personal,
        Other
    )

    private val tasks = mutableListOf( //-->> Lo que recibe aqui es lo que va a pintar este es 'mutable', puede cambiar.

        Task("PruebaBusiness", Business),
        Task("PruebaPersonal", Personal),
        Task("PruebaOther", Other)
    )

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var rvTasks: RecyclerView
    private lateinit var taskAdapter: TasksAdapter

    private lateinit var fabAddTask:FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        initComponent()
        initUI()
        initListeners()
    }


    private fun initComponent() {

        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
    }

    private fun initListeners() {

        fabAddTask.setOnClickListener{showDialog()}
    }

    private fun showDialog(){

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            val currentTask = etTask.text.toString()
            if (currentTask.isNotEmpty()) {

                val selectedId =
                    rgCategories.checkedRadioButtonId //--> accedemos al grupo de radioButton
                val selectedRadioButton: RadioButton =
                    rgCategories.findViewById(selectedId) //--> accedemos al seleccionado
                val currentCategory: TaskCategory =
                    when (selectedRadioButton.text) { //--> les asignamos la categoria a cada radio button
                        getString(R.string.todo_dialog_category_business) -> Business
                        getString(R.string.todo_dialog_category_personal) -> Personal
                        else -> Other
                    }

                tasks.add(Task(currentTask, currentCategory)) //--> se lo cargamos t odo a nuestra lista 'task'
                updateTask() //--> actualizamos la info
                dialog.hide() //--> para que se cierre la ventana cuando le demos a añadir tarea
            }
        }

        dialog.show()

    }
    private fun initUI() {

        categoriesAdapter = CategoriesAdapter(categories) {position -> updateCategories(position)}//--> le pasamos la listaaa
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) //la orientacion en la que se pinta
        rvCategories.adapter = categoriesAdapter

        taskAdapter = TasksAdapter(tasks) {position -> onItemSelected(position)} //--> position del TaskAdapter
        rvTasks.layoutManager = LinearLayoutManager(this) //--> como es vertical no hace falta poner nada mas
        rvTasks.adapter = taskAdapter
    }

    private fun onItemSelected(position: Int){

        tasks[position].isSelected = !tasks[position].isSelected
        updateTask()// para que actualice los datos
    }

    private fun updateCategories(position: Int){ //--> colores categorias

        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTask()

    }
    private fun updateTask(){ //--> esta funcion va a avisar al adaptador de que hay nuevos items

            //--> Para filtrar las categorias.
            val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
            val newTasks = tasks.filter { selectedCategories.contains(it.category) }
            taskAdapter.tasks = newTasks


            taskAdapter.notifyDataSetChanged() //-> los datos han sido actualizados.
    }

}