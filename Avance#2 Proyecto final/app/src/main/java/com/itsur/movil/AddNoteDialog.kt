package com.example.myapplication.ui

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.itsur.movil.R

import androidx.compose.ui.res.stringResource



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteDialog(
    onDismiss: () -> Unit,
    onAddNote: (com.itsur.movil.DataBase.Note) -> Unit,
    dispatchTakePictureIntent: () -> Unit,
    openGallery: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isTask by remember { mutableStateOf(false) }
    var dueDate by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(id = R.string.add_note)) },
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

                Button(onClick = { dispatchTakePictureIntent() }) {
                    Text(stringResource(id = R.string.capture_image))
                }
                Button(onClick = { openGallery() }) {
                    Text(stringResource(id = R.string.select_from_gallery))
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (title.isNotBlank() && description.isNotBlank()) {
                    onAddNote(
                        com.itsur.movil.DataBase.Note(
                            title = title,
                            description = description,
                            isTask = isTask,
                            dueDate = if (isTask) dueDate else null
                        )
                    )
                }
            }) {
                Text(stringResource(id = R.string.add))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.cancel))
            }
        }
    )
}
