import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composables.*
import de.comahe.i18n4k.Locale
import de.comahe.i18n4k.i18n4k
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun App() {
    var openedTab by remember { mutableStateOf(0) }
    var locale by remember{ mutableStateOf(i18n4k.locale) }

    MaterialTheme {

        Scaffold(
            topBar = {
                TabRow(selectedTabIndex = openedTab, tabs = {
                    Tab(
                        selected = openedTab == 0,
                        onClick = { openedTab = 0 },
                        text = { Text(MyMessages.extract_ds_file.toString(locale)) })
                    Tab(
                        selected = openedTab == 1,
                        onClick = {  openedTab = 1 },
                        text = { Text(MyMessages.extract_ds_folder.toString(locale)) })
                    Tab(
                        selected = openedTab == 2,
                        onClick = { openedTab = 2},
                        text = { Text(MyMessages.settings.toString(locale)) })
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
                    SettingsTab(onLocaleChange = { newLocale : Locale -> locale = newLocale })
                }

            }
        }

    }
}