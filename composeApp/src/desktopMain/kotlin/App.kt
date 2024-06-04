import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var openedTab by remember { mutableStateOf(0) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

            TabRow(selectedTabIndex = openedTab, tabs = {
                Tab(selected = openedTab == 0,
                    onClick = { openedTab = 0 },
                    text = { Text("Extract file") })
                Tab(
                    selected = openedTab == 1,
                    onClick = { openedTab = 1 },
                    text = { Text("Extract folder") })
                Tab(
                    selected = openedTab == 2,
                    onClick = { openedTab = 2 },
                    text = { Text("Settings") })
            })

            Row {
                if (openedTab == 0) {
                    ExtractFileTab()
                }
                if (openedTab == 1) {
                    ExtractFolderTab()
                }
                if (openedTab == 2) {
                    SettingsTab()
                }
            }
        }

    }
}
@Preview
@Composable
fun SettingsTab() {
    Text(text = "Settings")
}
@Preview
@Composable
fun ExtractFolderTab() {
    Text("Extract folder")
}
@Preview
@Composable
fun ExtractFileTab() {
    Text("Extract file")
}
