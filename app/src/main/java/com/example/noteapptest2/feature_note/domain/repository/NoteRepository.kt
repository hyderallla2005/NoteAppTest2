package com.example.noteapptest2.feature_note.domain.repository

import com.example.noteapptest2.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository{
    fun getNotes(): Flow<List<Note>>
    suspend fun getNoteById(noteId: Int):Note?

    suspend fun deleteNote(note: Note)
    suspend fun insertNote(note: Note)
}