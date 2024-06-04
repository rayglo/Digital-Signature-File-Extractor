package composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkrockstudios.libraries.mpfilepicker.DirectoryPicker
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import extractor.P7MExtractor
import org.jetbrains.compose.ui.tooling.preview.Preview
import java.io.File

@Preview
@Composable
fun ExtractFileTab() {
    val p7mExtractor = P7MExtractor()

    var isSingleFilePickerOpen by remember { mutableStateOf(false) }
    var isFolderFilePickerOpen by remember { mutableStateOf(false) }
    var selectedFilePath by remember { mutableStateOf<String?>(null) }
    var destinationFolderPath by remember { mutableStateOf<String?>(null) }

    var showSuccessDialog by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Extract Digitally Signed file",
            textDecoration = Underline,
            fontSize = 24.sp
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Select a file to extract",
            fontSize = 20.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier.weight(7f),
                value = selectedFilePath ?: "",
                onValueChange = { selectedFilePath = it },
                label = { Text("Selected file") },
                singleLine = true
            )
            Button(
                modifier = Modifier.weight(1f),
                onClick = { isSingleFilePickerOpen = true }) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Select a file"
                )
            }
            FilePicker(isSingleFilePickerOpen, fileExtensions = listOf("p7m")) {
                isSingleFilePickerOpen = false
                if (it?.platformFile as File? != null) {
                    selectedFilePath = ((it?.platformFile as File).absolutePath)
                }
                println("Selected file: $it")
            }
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Destination folder",
            fontSize = 20.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier.weight(7f),
                value = destinationFolderPath ?: "",
                onValueChange = { destinationFolderPath = it },
                label = { Text("Selected folder") },
                singleLine = true
            )
            Button(
                modifier = Modifier.weight(1f),
                onClick = { isFolderFilePickerOpen = true }) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Select a folder"
                )
            }

            DirectoryPicker(isFolderFilePickerOpen) {
                isFolderFilePickerOpen = false
                if (it != null) {
                    destinationFolderPath = it
                }
                println("Selected folder: $it")
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth().offset(y = 24.dp),
            enabled = selectedFilePath != null && destinationFolderPath != null,
            onClick = {
                println("Extracting file: $selectedFilePath to folder: $destinationFolderPath")
                val file = File(selectedFilePath!!)
                val extractedFile = p7mExtractor.extract(file, File(destinationFolderPath!!))
                assert(extractedFile != null)
                println("Extracted file: $extractedFile")
                showSuccessDialog = true
            }) {
            Text(
                textAlign = TextAlign.Center,
                text = "Extract file"
            )
        }

        when {
            showSuccessDialog -> {
                AlertDialog(
                    onDismissRequest = { showSuccessDialog = false },
                    title = { Text("File extracted successfully") },
                    text = { Text("The file has been extracted successfully") },
                    confirmButton = {
                        Button(
                            onClick = { showSuccessDialog = false }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}