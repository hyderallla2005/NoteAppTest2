package com.example.noteapptest2.feature_note.presentation.notes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.AnimationResult
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NoteAdd
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteapptest2.feature_note.domain.util.NoteOrder
import com.example.noteapptest2.feature_note.presentation.notes.component.NoteItem
import com.example.noteapptest2.feature_note.presentation.notes.component.OrderSection
import kotlinx.coroutines.launch

@Composable
fun NoteScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel = hiltViewModel(),
    ) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember{ SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.NoteAdd,
                    contentDescription = null
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Your Notes",
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                )
                IconButton(
                    onClick = {viewModel.onEvent(noteEvent = NoteEvent.ToggleOrderSection)}
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Sort,
                        contentDescription = null
                    )
                }
            }
            AnimatedVisibility(
                    visible = state.isOrderSectionVisible,
                enter = scaleIn() + expandVertically(),
                exit = scaleOut()
                ) {
                    OrderSection(
                        modifier = Modifier.fillMaxWidth(),
                        noteOrder = state.noteOrder
                    ) {
                        viewModel.onEvent(NoteEvent.Order(it))
                    } }
            LazyColumn(modifier = Modifier
                .fillMaxWidth() ){
                items(
                    state.notes
                ){note ->
                    NoteItem(
                        note = note,
                        onDeleteClick = {
                            viewModel.onEvent(NoteEvent.DeleteNote(note))
                            scope.launch {
                                val result=snackbarHostState.showSnackbar("Note is deleted", actionLabel = "Undo")
                                if(result == SnackbarResult.ActionPerformed){
                                    viewModel.onEvent(NoteEvent.RestoreNote)
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                }
            }
        }

    }
    }
