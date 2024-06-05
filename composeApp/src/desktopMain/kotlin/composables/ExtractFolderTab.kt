package composables

import MyMessages
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
import extractor.P7MExtractor
import org.jetbrains.compose.ui.tooling.preview.Preview
import java.io.File

@Composable
fun ExtractFolderTab() {
    val p7mExtractor = P7MExtractor()

    var isSourceFolderPickerOpen by remember { mutableStateOf(false) }
    var isDestinationFolderPickerOpen by remember { mutableStateOf(false) }
    var selectedSourcePath by remember { mutableStateOf<String?>(null) }
    var selectedDestinationPath by remember { mutableStateOf<String?>(null) }

    var showSuccessDialog by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = MyMessages.extract_digitally_signed_folder.toString(),
            textDecoration = Underline,
            fontSize = 24.sp
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = MyMessages.source_folder.toString(),
            fontSize = 20.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier.weight(6f),
                value = selectedSourcePath ?: "",
                onValueChange = { selectedDestinationPath = it },
                label = { Text(MyMessages.select_a_source_folder.toString()) },
                singleLine = true
            )
            Button(
                modifier = Modifier.weight(1f),
                onClick = { isSourceFolderPickerOpen = true }) {
                Text(
                    textAlign = TextAlign.Center,
                    text = MyMessages.select_a_folder.toString()
                )
            }
            DirectoryPicker(isSourceFolderPickerOpen) {
                isSourceFolderPickerOpen = false
                if (it != null) {
                    selectedSourcePath = it
                }
                println("(Folder extraction) Selected source folder: $it")
            }
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = MyMessages.destination_folder.toString(),
            fontSize = 20.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier.weight(6f),
                value = selectedDestinationPath ?: "",
                onValueChange = { selectedDestinationPath = it },
                label = { Text(MyMessages.select_a_destination_folder.toString()) },
                singleLine = true
            )
            Button(
                modifier = Modifier.weight(1f),
                onClick = { isDestinationFolderPickerOpen = true }) {
                Text(
                    textAlign = TextAlign.Center,
                    text = MyMessages.select_a_folder.toString()
                )
            }

            DirectoryPicker(isDestinationFolderPickerOpen) {
                isDestinationFolderPickerOpen = false
                if (it != null) {
                    selectedDestinationPath = it
                }
                println("(Folder extraction) Selected destination folder: $it")
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth().offset(y = 24.dp),
            enabled = selectedSourcePath != null && selectedDestinationPath != null,
            onClick = {
                println("Extracting folder: $selectedSourcePath to folder: $selectedDestinationPath")
                val sourceFolder = File(selectedSourcePath!!)
                val destinationFolder = File(selectedDestinationPath!!)

                val extractedFiles = p7mExtractor.extractDirectory(sourceFolder, destinationFolder)
                assert(extractedFiles.isNotEmpty())
                println("Extracted files: $extractedFiles")
                showSuccessDialog = true

            }) {
            Text(
                textAlign = TextAlign.Center,
                text = MyMessages.extract_folder.toString()
            )
        }

        when {
            showSuccessDialog -> {
                AlertDialog(
                    onDismissRequest = { showSuccessDialog = false },
                    title = { Text(MyMessages.folder_extracted_successfully.toString()) },
                    text = { Text(MyMessages.folder_extracted_successfully_desc.toString()) },
                    confirmButton = {
                        Button(
                            onClick = { showSuccessDialog = false }) {
                            Text(MyMessages.ok.toString())
                        }
                    }
                )
            }
        }
    }

}