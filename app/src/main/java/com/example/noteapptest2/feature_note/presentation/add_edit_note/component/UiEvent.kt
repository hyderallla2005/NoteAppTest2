package com.example.noteapptest2.feature_note.presentation.add_edit_note.component

sealed class UiEvent {
    data object SaveNote: UiEvent()
    data class ShowSnackbar(val message: String): UiEvent()
}