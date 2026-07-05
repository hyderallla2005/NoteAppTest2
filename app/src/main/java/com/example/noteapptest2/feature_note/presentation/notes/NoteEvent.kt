package com.example.noteapptest2.feature_note.presentation.notes

import com.example.noteapptest2.feature_note.domain.model.Note
import com.example.noteapptest2.feature_note.domain.util.NoteOrder

sealed class NoteEvent {
    data class DeleteNote(val note: Note): NoteEvent()
    data class Order(val noteOrder: NoteOrder) : NoteEvent()
    object RestoreNote : NoteEvent()
    object ToggleOrderSection: NoteEvent()
    }