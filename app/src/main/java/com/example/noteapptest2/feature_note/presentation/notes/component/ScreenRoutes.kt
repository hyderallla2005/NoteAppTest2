package com.example.noteapptest2.feature_note.presentation.notes.component

import kotlinx.serialization.Serializable

sealed class ScreenRoutes {
    @Serializable
    data object NoteScreen
    @Serializable
    data class AddEditScreen(val noteId: Int)
}