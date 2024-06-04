import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composables.*
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun App() {
    val viewModel = NavbarViewModel.getInstance()

    MaterialTheme {

        Scaffold(
            topBar = {
                Navbar(viewModel)
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                if (viewModel.openedTab == 0) {
                    ExtractFileTab()
                }
                if (viewModel.openedTab == 1) {
                    ExtractFolderTab()
                }
                if (viewModel.openedTab == 2) {
                    SettingsTab()
                }

            }
        }

    }
}