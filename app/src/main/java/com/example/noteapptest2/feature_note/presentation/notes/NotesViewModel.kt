package com.example.noteapptest2.feature_note.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapptest2.feature_note.domain.model.Note
import com.example.noteapptest2.feature_note.domain.use_case.NoteUseCases
import com.example.noteapptest2.feature_note.domain.util.NoteOrder
import com.example.noteapptest2.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NotesViewModel @Inject constructor(
    private val useCases: NoteUseCases
) : ViewModel(){
    private val _state = MutableStateFlow(NoteState())
    val state: StateFlow<NoteState> =_state.asStateFlow()
    private var recentlyDeletedNote: Note? =null
    var getNoteJob: Job?=null
init {
    getNotes(NoteOrder.Date(OrderType.Descending))
}
    fun onEvent(noteEvent: NoteEvent){
        when(noteEvent){
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    useCases.deleteNoteUseCase(noteEvent.note)
                    recentlyDeletedNote=noteEvent.note
                }
            }
            is NoteEvent.Order -> {
                      if(_state.value.noteOrder::class == noteEvent.noteOrder::class &&
                          _state.value.noteOrder.orderType == noteEvent.noteOrder.orderType){
                          return
                      }
                    getNotes(noteEvent.noteOrder)
            }
            is NoteEvent.RestoreNote -> {
                viewModelScope.launch {
                    useCases.addNoteUseCase(recentlyDeletedNote ?:return@launch)
                    recentlyDeletedNote =null
                }
            }
            is NoteEvent.ToggleOrderSection -> {
                _state.update { it.copy(
                    isOrderSectionVisible = !it.isOrderSectionVisible
                ) }
            }
        }
    }
     fun getNotes(noteOrder: NoteOrder){
         getNoteJob?.cancel()
        getNoteJob=useCases.getNoteUseCase(noteOrder).onEach { notes ->
            _state.update { it.copy(
                notes = notes ,
                noteOrder = noteOrder
            )
            }
        }.launchIn(viewModelScope)
    }

}