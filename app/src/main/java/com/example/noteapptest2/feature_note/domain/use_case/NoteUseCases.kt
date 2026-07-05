package com.example.noteapptest2.feature_note.domain.use_case

import javax.inject.Inject

data class NoteUseCases @Inject constructor(
    val getNoteUseCase: GetNoteUseCase,
    val getNoteByIdUseCase: GetNoteByIdUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val addNoteUseCase: AddNoteUseCase
)
