package com.example.myapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.itsur.movil.DataBase.Note

import com.itsur.movil.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteDialog(
    note: Note,
    onDismiss: () -> Unit,
    onSaveNote: (Note) -> Unit,
    onDeleteNote: () -> Unit
) {
    var title by remember { mutableStateOf(note.title) }
    var description by remember { mutableStateOf(note.description) }
    var isTask by remember { mutableStateOf(note.isTask) }
    var dueDate by remember { mutableStateOf(note.dueDate ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(id = R.string.edit_note)) },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(stringResource(id = R.string.title)) }
                )
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(stringResource(id = R.string.description)) }
                )

                Row {
                    Text(stringResource(id = R.string.is_task))
                    Checkbox(checked = isTask, onCheckedChange = { isTask = it })
                }

                if (isTask) {
                    TextField(
                        value = dueDate,
                        onValueChange = { dueDate = it },
                        label = { Text(stringResource(id = R.string.task_due_date)) }
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (title.isNotBlank() && description.isNotBlank()) {
                    onSaveNote(
                        note.copy(
                            title = title,
                            description = description,
                            isTask = isTask,
                            dueDate = dueDate
                        )
                    )
                }
            }) {
                Text(stringResource(id = R.string.save))
            }
        },
        dismissButton = {
            Row {
                TextButton(onClick = onDismiss) {
                    Text(stringResource(id = R.string.cancel))
                }
                Spacer(Modifier.width(8.dp))
                TextButton(onClick = onDeleteNote) {
                    Text(stringResource(id = R.string.delete))
                }
            }
        }
    )
}
