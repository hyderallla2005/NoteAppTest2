package com.example.noteapptest2.feature_note.presentation.add_edit_note

import com.example.noteapptest2.feature_note.domain.model.Note

data class AddEditNoteState(
    val note: Note?=null,
    val currentTitle: String ="",
    val currentContent: String ="",
    val currentColor: Int?=null
)
