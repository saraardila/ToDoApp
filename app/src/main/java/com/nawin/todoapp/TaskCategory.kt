package com.nawin.cursokotlin.todoapp

sealed class TaskCategory(var isSelected:Boolean = true) { //-->Clase sellada para crear objetos

    object Personal:TaskCategory()
    object Business:TaskCategory()
    object Other:TaskCategory()
}

