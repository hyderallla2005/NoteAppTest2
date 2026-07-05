package com.example.noteapptest2.feature_note.presentation.add_edit_note

sealed class AddEditEvent {
    data class EnteredTitle(val value: String): AddEditEvent()
    data class EnteredContent(val value: String): AddEditEvent()
    data class ChangeColor(val color: Int): AddEditEvent()
    data object SaveNote: AddEditEvent()
}