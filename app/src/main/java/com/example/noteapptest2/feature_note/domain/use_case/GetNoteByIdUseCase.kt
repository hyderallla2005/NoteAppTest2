package com.example.noteapptest2.feature_note.domain.use_case

import com.example.noteapptest2.feature_note.domain.model.Note
import com.example.noteapptest2.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    val repository: NoteRepository
) {
    suspend operator fun invoke(noteId: Int):Note?{
        return repository.getNoteById(noteId)
    }
}