package com.example.noteapptest2.feature_note.domain.util

sealed class NoteOrder(val orderType: OrderType) {
    class Title(order: OrderType) : NoteOrder(order)
    class Date(order: OrderType) : NoteOrder(order)
    class Color(order: OrderType) : NoteOrder(order)

    fun copy(orderType: OrderType): NoteOrder{
        return when(this) {
            is Color -> Color(orderType)
            is Date -> Date(orderType)
            is Title -> Title(orderType)
        }
    }
}