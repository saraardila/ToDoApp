package com.nawin.cursokotlin.todoapp

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nawin.todoapp.R

class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) { //--> para pintar el recycler

    private val tvTask: TextView = view.findViewById(R.id.tvTask)
    private val cbTask: CheckBox = view.findViewById(R.id.cbTask)

    fun render(task: Task) {

        if (task.isSelected) { //--> logica para que se tachen los checkbox

            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        }else {

            tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv() //-- invertido para que se destache.
        }


        tvTask.text = task.name
        cbTask.isChecked = task.isSelected ///-->> para que se ponga el check.

        val color = when(task.category){ //--> para cambiar el color de los checks.

            TaskCategory.Business -> R.color.todo_business_category
            TaskCategory.Other -> R.color.todo_other_category
            TaskCategory.Personal -> R.color.todo_personal_category
        }

        cbTask.buttonTintList = ColorStateList.valueOf(

            ContextCompat.getColor(cbTask.context,color) //--> para acceder a la vista y el color asignado del when
        )

    }

}