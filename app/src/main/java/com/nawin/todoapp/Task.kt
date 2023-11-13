package com.nawin.cursokotlin.todoapp

data class Task (val name:String, val category: TaskCategory, var isSelected:Boolean=false) //--> clase que va a tener varios atributos

//--> el nombre, la categoria y si está seleccionado o no.
