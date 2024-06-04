import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composables.ExtractFileTab
import composables.ExtractFolderTab
import composables.SettingsTab
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun App() {
    MaterialTheme {
        var openedTab by remember { mutableStateOf(0) }

        Scaffold(
            topBar = {
                TabRow(selectedTabIndex = openedTab, tabs = {
                    Tab(selected = openedTab == 0,
                        onClick = { openedTab = 0 },
                        text = { Text("Extract DS file") })
                    Tab(
                        selected = openedTab == 1,
                        onClick = { openedTab = 1 },
                        text = { Text("Extract DS folder") })
                    Tab(
                        selected = openedTab == 2,
                        onClick = { openedTab = 2 },
                        text = { Text("Settings") })
                })
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
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





