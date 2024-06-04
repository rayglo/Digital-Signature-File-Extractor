package composables

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import de.comahe.i18n4k.i18n4k

class NavbarViewModel private constructor() {

    companion object {
        @Volatile
        private var instance: NavbarViewModel? = null

        fun getInstance(): NavbarViewModel {
            return instance ?: synchronized(this) {
                instance ?: NavbarViewModel().also { instance = it }
            }
        }
    }

    var openedTab by mutableStateOf(0)
}

@Composable
fun Navbar(viewModel: NavbarViewModel){
    NavbarContent(openedTab = viewModel.openedTab, onTabSelected = { viewModel.openedTab = it })
}

@Composable
fun NavbarContent(openedTab: Int, onTabSelected: (Int) -> Unit) {
    val locale = i18n4k.locale

    TabRow(selectedTabIndex = openedTab, tabs = {
        Tab(
            selected = openedTab == 0,
            onClick = { onTabSelected(0) },
            text = { Text(MyMessages.extract_ds_file.toString(locale)) })
        Tab(
            selected = openedTab == 1,
            onClick = {  onTabSelected(1) },
            text = { Text(MyMessages.extract_ds_folder.toString(locale)) })
        Tab(
            selected = openedTab == 2,
            onClick = { onTabSelected(2)},
            text = { Text(MyMessages.settings.toString(locale)) })
    })
}