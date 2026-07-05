package com.example.noteapptest2.feature_note.presentation.add_edit_note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.noteapptest2.feature_note.domain.model.InvalidNoteException
import com.example.noteapptest2.feature_note.domain.model.Note
import com.example.noteapptest2.feature_note.domain.use_case.NoteUseCases
import com.example.noteapptest2.feature_note.presentation.add_edit_note.component.UiEvent
import com.example.noteapptest2.feature_note.presentation.notes.component.ScreenRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val useCase: NoteUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    init {
        val args=savedStateHandle.toRoute<ScreenRoutes.AddEditScreen>()
        if(args.noteId !=-1){
            getNoteById(args.noteId)
        }
    }
    private val _state = MutableStateFlow(AddEditNoteState())
    val state: StateFlow<AddEditNoteState> =_state.asStateFlow()

    private val _uiState =Channel<UiEvent>()
            val uiState =_uiState.receiveAsFlow()

    fun onEvent(event: AddEditEvent){
        when(event) {
            is AddEditEvent.ChangeColor -> {
                _state.update { it.copy(
                    currentColor = event.color
                ) }
            }
            is AddEditEvent.EnteredContent -> {
                _state.update { it.copy(
                    currentContent = event.value
                ) }
            }
            is AddEditEvent.EnteredTitle -> {
                _state.update {
                    it.copy(
                        currentTitle = event.value
                    )
                }
            }
            is AddEditEvent.SaveNote -> {
                viewModelScope.launch {
                    try {

                        useCase.addNoteUseCase(
                            Note(
                                title = _state.value.currentTitle,
                                content = _state.value.currentContent,
                                color = _state.value.currentColor ?: 0,
                                timestamp = System.currentTimeMillis(),
                                id = _state.value.note?.id
                            )
                        )
                        _uiState.send(UiEvent.SaveNote)

                    } catch (e: InvalidNoteException) {
                        _uiState.send(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save a note"
                            )
                        )
                    }
                }
            }
        }
    }
    fun getNoteById(noteId: Int){
        viewModelScope.launch {
            val note=useCase.getNoteByIdUseCase(noteId)
            note?.let {note ->
                _state.update { it.copy(
                        note = note,
                        currentTitle = note.title,
                        currentContent = note.content,
                        currentColor = note.color
                    )
                }
            }
        }
        }

    }

