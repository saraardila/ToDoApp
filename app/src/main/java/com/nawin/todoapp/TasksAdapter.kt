package com.nawin.cursokotlin.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nawin.todoapp.R

class TasksAdapter ( var tasks:List<Task>, private val onTaskSelected:(Int) -> Unit) : RecyclerView.Adapter<TasksViewHolder>() { //--> adaptador del recycler, le pasamos los objetos que creamos en TaskCategory
    //--> cuando lo creamos sale error, y sale para importar los override de abajo.
//--> la funcion labda es el onTaskSelected que devuelve un int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        //--> crea una vista

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo_task, parent, false) //-->> el inflador de la vista
        return TasksViewHolder(view)//--> llamamos el viewholder que tiene la vista.
    }

    override fun getItemCount(): Int = tasks.size  //--> es un return de kotlin que me devuelve el tamaño de las categorias que hay.

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {

        holder.render(tasks[position]) //-->> llamamos a las posiciones desde la funcion render del viewHolder, para pintar el item correcto.
        holder.itemView.setOnClickListener{onTaskSelected(position)}
        //onTaskSelected(position)

    }
}