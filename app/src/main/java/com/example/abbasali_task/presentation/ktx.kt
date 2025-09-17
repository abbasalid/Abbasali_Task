package com.example.abbasali_task.presentation

fun Double.format(decimals: Int): String =
    "%,.${decimals}f".format(this)