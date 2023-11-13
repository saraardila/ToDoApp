package com.nawin.cursokotlin.todoapp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nawin.todoapp.R


class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) { //--> para pintar el recycler

    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val divider: View = view.findViewById(R.id.divider)
    private val viewContainer: CardView = view.findViewById(R.id.viewContainer)

    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {
        //--> para la logica de marcar y desmarcar
       val color =  if(taskCategory.isSelected){
            R.color.todo_background_card
        }else{
            R.color.todo_background_disabled
        }

        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context, color))

        itemView.setOnClickListener{onItemSelected(layoutPosition)}


        when (taskCategory) { //--> Llamamos a los objetos
            //--> si le damos a la bombilla roja nos lo importa solo lo de abajo

            TaskCategory.Business -> {
                tvCategoryName.text = "Negocios"
                divider.setBackgroundColor( //--> le decimos que color en esa categoria.
                    ContextCompat.getColor(divider.context, R.color.todo_business_category)
                )
            }
            TaskCategory.Other -> {
                tvCategoryName.text = "Otros"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_other_category)
                )
            }
            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_personal_category)
                )
            }
        }
    }
}