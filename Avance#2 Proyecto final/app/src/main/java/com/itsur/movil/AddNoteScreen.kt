package com.example.myapplication.ui

import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.BoxWithConstraints
import com.example.myapplication.viewmodel.NoteViewModel

@Composable
fun AddNoteScreen(noteViewModel: NoteViewModel) {
    BoxWithConstraints {
        if (maxWidth < 600.dp) {
            AddNoteScreenCompact(noteViewModel)
        } else {
            AddNoteScreenExpanded(noteViewModel)
        }
    }
}

@Composable
fun AddNoteScreenCompact(noteViewModel: NoteViewModel) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AddNoteDialog(
            onDismiss = { showDialog = false },
            onAddNote = { note ->
                noteViewModel.addNote(note)
                showDialog = false
            },
            dispatchTakePictureIntent = { /* Implementa tu lógica de captura de imagen */ },
            openGallery = { /* Implementa tu lógica para abrir la galería */ }
        )
    }
}

@Composable
fun AddNoteScreenExpanded(noteViewModel: NoteViewModel) {
    // Similares ajustes pero con mayor espacio para más contenido
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AddNoteDialog(
            onDismiss = { showDialog = false },
            onAddNote = { note ->
                noteViewModel.addNote(note)
                showDialog = false
            },
            dispatchTakePictureIntent = { /* Implementa tu lógica de captura de imagen */ },
            openGallery = { /* Implementa tu lógica para abrir la galería */ }
        )
    }
}
