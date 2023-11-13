package com.nawin.cursokotlin.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nawin.todoapp.R

class CategoriesAdapter(private val categories: List<TaskCategory>, private val onItemSelected:(Int) -> Unit) :
    RecyclerView.Adapter<CategoriesViewHolder>() { //--> adaptador del recycler, le pasamos los objetos que creamos en TaskCategory
    //--> cuando lo creamos sale error, y sale para importar los override de abajo.

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
       //--> crea una vista

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false) //-->> el inflador de la vista
        return CategoriesViewHolder(view)//--> llamamos el viewholder que tiene la vista.
    }


    //--> es un return de kotlin que me devuelve el tamaño de las categorias que hay.
    override fun getItemCount(): Int =  categories.size


    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {

        holder.render(categories[position], onItemSelected) //-->> llamamos a las posiciones desde la funcion render del viewHolder, para pintar el item correcto.
    }


}