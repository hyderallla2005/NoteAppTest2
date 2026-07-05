package com.example.noteapptest2.feature_note.domain.use_case

import com.example.noteapptest2.feature_note.domain.model.InvalidNoteException
import com.example.noteapptest2.feature_note.domain.model.Note
import com.example.noteapptest2.feature_note.domain.repository.NoteRepository
import javax.inject.Inject
import kotlin.jvm.Throws

class   AddNoteUseCase @Inject constructor (
    val repository: NoteRepository
) {
    @Throws
    suspend operator fun invoke(note: Note){
        if(note.title.isBlank()){
            throw InvalidNoteException("No title... enter a title")
        }
        if(note.content.isBlank()){
            throw InvalidNoteException("No content are there plz enter a content..")
        }
        repository.insertNote(note)
    }
}